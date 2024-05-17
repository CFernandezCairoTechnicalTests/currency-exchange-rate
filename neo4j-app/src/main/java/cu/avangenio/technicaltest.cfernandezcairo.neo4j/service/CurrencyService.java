package cu.avangenio.technicaltest.cfernandezcairo.neo4j.service;

import cu.avangenio.technicaltest.cfernandezcairo.neo4j.dto.EventFindSubgraphDTO;
import cu.avangenio.technicaltest.cfernandezcairo.neo4j.kafka.KafkaProducer;
import cu.avangenio.technicaltest.cfernandezcairo.neo4j.model.node.Currency;
import cu.avangenio.technicaltest.cfernandezcairo.neo4j.model.relationship.Rate;
import cu.avangenio.technicaltest.cfernandezcairo.neo4j.repository.CurrencyRepository;
import org.neo4j.driver.internal.InternalRelationship;
import org.neo4j.driver.internal.value.ListValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;



@Service
public class CurrencyService {
    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private KafkaProducer kafkaProducer;

    public Currency update(Currency currency) {
        return currencyRepository.save(currency);
    }

    public Currency createOrUpdate(Currency currency) {
        Optional<Currency> monedaInDBOptional = currencyRepository.findById(currency.getTitle());

        Set<Rate> monedaRates = new HashSet<Rate>();
        monedaRates.addAll(currency.getRates());

        Currency currencyInDB = null;
        Set<Rate> monedaInDBRatesRates = null;
        if(monedaInDBOptional.isPresent()) {
            currencyInDB = monedaInDBOptional.get();
            monedaInDBRatesRates = currencyInDB.getRates();
            monedaInDBRatesRates.addAll(currency.getRates());

            currencyInDB.setRates(monedaInDBRatesRates);
            currencyInDB = currencyRepository.save(currencyInDB);

        } else {
            currencyInDB = currencyRepository.save(currency);
        }

        return currencyInDB;
    }
    @Transactional
    public List<Currency> batchCreateOrUpdate(List<Currency> currencyList) {
        List<Currency> result = new ArrayList<Currency>();
        for (Currency m : currencyList) {
            createOrUpdate(m);
            result.add(m);
        }
        return result;
    }

    public List<EventFindSubgraphDTO> findSubGraph(String from, String to, LocalDateTime start) {
        List<Object> result = currencyRepository.findSubGraph(from, to, start);

        List<EventFindSubgraphDTO> exchangeProcessResult = new ArrayList<>();
        UUID exchangeProcessResultKey = UUID.randomUUID();

        Integer currentAmountExchage;
        Double currentExchange;
        LocalDateTime currentMediaDateExchange;

        EventFindSubgraphDTO currentExchangeOpProcess;
        for(Object relationShip : result) {

            currentExchangeOpProcess = EventFindSubgraphDTO
                    .builder()
                    .requestKey(exchangeProcessResultKey)
                    .key(UUID.randomUUID())
                    .from(from)
                    .to(to)
                    .build();

            currentAmountExchage = 0;
            currentExchange = 1.0;
            currentMediaDateExchange = null;
            for(Object relation : ((ListValue) relationShip).asList()) {

                String currentRate = (String) ((InternalRelationship) relation).asMap().get("rate");
                LocalDateTime currentDate = (LocalDateTime) ((InternalRelationship) relation).asMap().get("date");

                if(currentMediaDateExchange == null) {
                    currentMediaDateExchange = currentDate;
                } else {
                    if(currentDate.isBefore(currentMediaDateExchange)) {
                        currentMediaDateExchange = getMediaDate(currentDate, currentMediaDateExchange);
                    } else {
                        currentMediaDateExchange = getMediaDate(currentMediaDateExchange, currentDate);
                    }
                }
                currentAmountExchage++;
                currentExchange*=Float.valueOf(currentRate);

            }
            currentExchangeOpProcess.setAmountExchange(currentAmountExchage);
            currentExchangeOpProcess.setFinalExchange(currentExchange);
            currentExchangeOpProcess.setMediaDateExchange(currentMediaDateExchange);

            kafkaProducer.sendEvent(currentExchangeOpProcess.getKey().toString(), currentExchangeOpProcess);
            exchangeProcessResult.add(currentExchangeOpProcess);

        }

        return exchangeProcessResult;
    }

    private LocalDateTime getMediaDate(LocalDateTime start, LocalDateTime end) {
        long numHours = ChronoUnit.HOURS.between(start, end);
        return start.plusHours(numHours / 2L);
    }
}
