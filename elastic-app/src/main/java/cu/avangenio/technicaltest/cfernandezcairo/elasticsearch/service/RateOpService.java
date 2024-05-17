package cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.service;

import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.model.RateOp;
import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.service.exception.DuplicateIdException;
import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.service.exception.RateNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RateOpService {

    Optional<RateOp> getById(UUID id);

    List<RateOp> getAll();

    List<RateOp> findByFromCurrency(String from);

    RateOp create(RateOp rateOp) throws DuplicateIdException;

    void deleteById(UUID id);

    RateOp update(UUID id, RateOp rateOp) throws RateNotFoundException;
}
