package cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.service;

import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.model.ServiceSubgraph;
import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.service.exception.DuplicateIdException;

import java.util.Optional;
import java.util.UUID;

public interface ServiceSubgraphService {

    Optional<ServiceSubgraph> getById(UUID id);

    ServiceSubgraph create(ServiceSubgraph serviceSubgraph) throws DuplicateIdException;

}
