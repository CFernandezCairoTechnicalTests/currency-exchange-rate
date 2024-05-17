package cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.repository;

import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.model.ServiceSubgraph;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ServiceSubgraphRepository extends ElasticsearchRepository<ServiceSubgraph, UUID> {
}