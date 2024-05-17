package cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.repository;

import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.model.RateOp;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RateRepository extends ElasticsearchRepository<RateOp, UUID> {
    List<RateOp> findByFrom(String fromCurrency);
}