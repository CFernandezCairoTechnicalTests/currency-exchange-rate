package cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.service.impl;

import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.RateElasticsearchContainer;
import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.model.RateOp;
import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.service.RateOpService;
import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.service.exception.DuplicateIdException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@Testcontainers
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DefaultRateOpServiceTest {

    @Autowired
    private RateOpService rateOpService;

    @Autowired
    private ElasticsearchTemplate template;

    @Container
    private static final ElasticsearchContainer elasticsearchContainer = new RateElasticsearchContainer();

    @BeforeAll
    static void setUp() {
        elasticsearchContainer.start();
    }

    @BeforeEach
    void testIsContainerRunning() {
        assertTrue(elasticsearchContainer.isRunning());
        recreateIndex();
    }

    @Test
    void testGetRateById() throws DuplicateIdException {

        UUID testId = UUID.randomUUID();

        rateOpService.create(createRateOp(
                testId,
                "CUP",
                "USD",
                385));
        Optional<RateOp> result = rateOpService.getById(testId);
        assertTrue(result.isPresent());

        RateOp createdRateOp = result.get();
        assertNotNull(createdRateOp);
        assertEquals("CUP", createdRateOp.getFrom());
        assertEquals("USD", createdRateOp.getTo());
        assertEquals(385, createdRateOp.getRate());
        assertEquals(testId, createdRateOp.getKey());
    }

    @Test
    void testGetAllRates() throws DuplicateIdException {
        rateOpService.create(createRateOp(
                UUID.randomUUID(),
                "CUP",
                "USD",
                385));
        rateOpService.create(createRateOp(
                UUID.randomUUID(),
                "CUP",
                "EUR",
                395));
        List<RateOp> rateOpList = rateOpService.getAll();

        assertNotNull(rateOpList);
        assertTrue(rateOpList.size() >= 2);
    }

    @Test
    void testFindByFrom() throws DuplicateIdException {
        rateOpService.create(createRateOp(
                UUID.randomUUID(),
                "CUP",
                "USD",
                385));
        rateOpService.create(createRateOp(
                UUID.randomUUID(),
                "CUP",
                "EUR",
                395));

        List<RateOp> rateOps = rateOpService.findByFromCurrency("CUP");

        assertNotNull(rateOps);
        assertEquals(2, rateOps.size());
    }


    private RateOp createRateOp(UUID key, String fromCurrency, String toCurrency, float rate) {
        RateOp rateOp = RateOp.builder()
                .key(key)
                .from(fromCurrency)
                .to(toCurrency)
                .date(LocalDateTime.now())
                .rate(rate)
                .build();
        return rateOp;
    }

    private void recreateIndex() {
        if (template.indexOps(RateOp.class).exists()) {
            template.indexOps(RateOp.class).delete();
            template.indexOps(RateOp.class).create();
        }
    }

    @AfterAll
    static void destroy() {
        elasticsearchContainer.stop();
    }
}
