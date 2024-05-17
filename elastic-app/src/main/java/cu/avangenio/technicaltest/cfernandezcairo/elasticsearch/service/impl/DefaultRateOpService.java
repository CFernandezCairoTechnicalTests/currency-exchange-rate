package cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.service.impl;

import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.model.RateOp;
import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.repository.RateRepository;
import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.service.RateOpService;
import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.service.exception.DuplicateIdException;
import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.service.exception.RateNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders.match;

@Service
public class DefaultRateOpService implements RateOpService {

    @Autowired
    private RateRepository rateRepository;

    @Override
    public Optional<RateOp> getById(UUID id) {
        return rateRepository.findById(id);
    }

    @Override
    public List<RateOp> getAll() {
        List<RateOp> rateOps = new ArrayList<>();
        rateRepository.findAll()
            .forEach(rateOps::add);
        return rateOps;
    }

    @Override
    public List<RateOp> findByFromCurrency(String from) {
        return rateRepository.findByFrom(from);
    }

    @Override
    public RateOp create(RateOp rateOp) throws DuplicateIdException {
        Optional<RateOp> rateInDB = getById(rateOp.getKey());

        if (rateInDB.isEmpty()) {
            return rateRepository.save(rateOp);
        }

        throw new DuplicateIdException(String.format("The provided ID: %s already exists. Use update instead!", rateOp.getKey()));
    }

    @Override
    public void deleteById(UUID id) {
        rateRepository.deleteById(id);
    }

    @Override
    public RateOp update(UUID id, RateOp rateOp) throws RateNotFoundException {
        RateOp oldRateOp = rateRepository.findById(id)
            .orElseThrow(() -> new RateNotFoundException("There is not rate associated with the given id"));
        oldRateOp.setFrom(rateOp.getFrom());
        oldRateOp.setTo(rateOp.getTo());
        oldRateOp.setRate(rateOp.getRate());
        return rateRepository.save(oldRateOp);
    }
}
