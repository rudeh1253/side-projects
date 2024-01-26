package personal.nsl.container.repository;

import java.util.Optional;

import personal.nsl.container.domain.TermContainer;

public interface TermContainerRepository {

    public void save(TermContainer container);
    
    public boolean deleteByName(String name);
    
    public Optional<TermContainer> findByName(String name);
}
