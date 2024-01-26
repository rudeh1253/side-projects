package personal.nsl.container.repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import personal.nsl.container.domain.TermContainer;

public class MemoryTermContainerRepository implements TermContainerRepository {
    private static final Map<String, TermContainer> repository = new ConcurrentHashMap<>();

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
        return Optional.of(repository.get(name));
    }

}
