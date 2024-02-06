package personal.nsl.termlist.container.repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import personal.nsl.termlist.container.domain.TermContainer;
import personal.nsl.termlist.util.NLogger;

public class MemoryTermContainerRepository implements TermContainerRepository {
    private final Map<String, TermContainer> repository = new ConcurrentHashMap<>();
    private final NLogger log = NLogger.getLogger();

    @Override
    public void save(TermContainer container) {
        repository.put(container.getName(), container);
    }

    @Override
    public boolean deleteByName(String name) {
        TermContainer result = repository.remove(name);
        log.log(result, getClass());
        return result != null;
    }

    @Override
    public Optional<TermContainer> findByName(String name) {
        Optional<TermContainer> found = Optional.ofNullable(repository.get(name));
        log.log(found, getClass());
        return found;
    }

}
