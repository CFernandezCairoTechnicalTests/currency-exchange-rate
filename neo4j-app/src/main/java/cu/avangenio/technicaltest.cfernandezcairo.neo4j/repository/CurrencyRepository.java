package cu.avangenio.technicaltest.cfernandezcairo.neo4j.repository;

import cu.avangenio.technicaltest.cfernandezcairo.neo4j.model.node.Currency;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyRepository extends Neo4jRepository<Currency, String> {
    @Query("MATCH p = (u1:Curency)-[*0..1]->(:Curency) " +
            "WHERE u1.title =~ $title " +
            "RETURN u1, collect(relationships(p)), collect(nodes(p))")
    Optional<Currency> findByTitle(String title);

    @Query("MATCH p = (x:Curency {title: $from})-[r:RATE*]->(z:Curency {title: $to})\n" +
            "where all(rel in r WHERE LocalDateTime(rel.date) <= LocalDateTime($start))\n" +
            "RETURN r as relations\n" +
            "Limit 10")
    List<Object> findSubGraph(String from, String to, LocalDateTime start);

    /*@Query("MATCH p = (x:Curency {title: $from})-[r:RATE*]->(z:Curency {title: $to})\n" +
            "where all(rel in r WHERE LocalDateTime(rel.date) >= LocalDateTime($start) and LocalDateTime(rel.date) <= LocalDateTime($end))\n" +
            "RETURN x as from, r as relations, z as to")
    Iterable<Map<String,Object>> findSubGraph(String from, String to, LocalDateTime start, LocalDateTime end);*/
}
