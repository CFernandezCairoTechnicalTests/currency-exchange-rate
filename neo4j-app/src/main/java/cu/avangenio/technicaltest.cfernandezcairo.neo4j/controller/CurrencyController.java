package cu.avangenio.technicaltest.cfernandezcairo.neo4j.controller;

import cu.avangenio.technicaltest.cfernandezcairo.neo4j.dto.EventFindSubgraphDTO;
import cu.avangenio.technicaltest.cfernandezcairo.neo4j.dto.RequestDTO;
import cu.avangenio.technicaltest.cfernandezcairo.neo4j.model.node.Currency;
import cu.avangenio.technicaltest.cfernandezcairo.neo4j.service.CurrencyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("currency")
@Tag(name = "Waste Manager Operations", description = "Waste manager operations")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/subgraph")
    public ResponseEntity<?> getSubGraph(@Valid @RequestBody RequestDTO requestDTO){

        List<EventFindSubgraphDTO> relations = currencyService.findSubGraph(requestDTO.getFrom(), requestDTO.getTo(), requestDTO.getStart());

        if (relations.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(relations);
    }

}
