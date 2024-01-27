package personal.nsl.termlist.container.repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import personal.nsl.termlist.container.domain.TermContainer;

public class MemoryTermContainerRepository implements TermContainerRepository {
    private final Map<String, TermContainer> repository = new ConcurrentHashMap<>();

    @Override
    public void save(TermContainer container) {
        repository.put(container.getName(), container);
    }

    @Override
    public boolean deleteByName(String name) {
        TermContainer result = repository.remove(name);
        return result != null;
    }

    @Override
    public Optional<TermContainer> findByName(String name) {
        return Optional.ofNullable(repository.get(name));
    }

}
