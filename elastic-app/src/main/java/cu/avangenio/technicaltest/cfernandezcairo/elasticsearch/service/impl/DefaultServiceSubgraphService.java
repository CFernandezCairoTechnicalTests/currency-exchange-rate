package cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.service.impl;

import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.model.ServiceSubgraph;
import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.repository.ServiceSubgraphRepository;
import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.service.ServiceSubgraphService;
import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.service.exception.DuplicateIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultServiceSubgraphService implements ServiceSubgraphService {

    @Autowired
    private ServiceSubgraphRepository serviceSubgraphRepository;

    @Override
    public Optional<ServiceSubgraph> getById(UUID id) {
        return serviceSubgraphRepository.findById(id);
    }

    @Override
    public ServiceSubgraph create(ServiceSubgraph serviceSubgraph) throws DuplicateIdException {
        if (getById(serviceSubgraph.getKey()).isEmpty()) {
            return serviceSubgraphRepository.save(serviceSubgraph);
        }
        throw new DuplicateIdException(String.format("The provided ID: %s already exists. Use update instead!", serviceSubgraph.getKey()));
    }
}
