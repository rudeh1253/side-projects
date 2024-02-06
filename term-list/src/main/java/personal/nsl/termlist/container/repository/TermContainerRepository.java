package personal.nsl.termlist.container.repository;

import java.util.Optional;

import personal.nsl.termlist.container.domain.TermContainer;

public interface TermContainerRepository {
    
    public static TermContainerRepository getInstance() {
        return MemoryTermContainerRepository.getInstance();
    }

    public boolean save(TermContainer container);
    
    public boolean deleteByName(String name);
    
    public Optional<TermContainer> findByName(String name);
}
