package personal.nsl.termlist.container.repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import personal.nsl.termlist.container.domain.TermContainer;
import personal.nsl.termlist.util.NLogger;

public class MemoryTermContainerRepository implements TermContainerRepository {
    private static final MemoryTermContainerRepository instance = new MemoryTermContainerRepository();
    private static final NLogger log = NLogger.getLogger();
    
    private final Map<String, TermContainer> repository = new ConcurrentHashMap<>();
    
    private MemoryTermContainerRepository() {
    }
    
    public static TermContainerRepository getInstance() {
        return instance;
    }

    @Override
    public boolean save(TermContainer container) {
        log.log(container);
        
        if (!this.repository.containsKey(container.getName())) {
            repository.put(container.getName(), container);
            log.log("Succeeded to save", getClass());
            return true;
        }
        return false;
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
