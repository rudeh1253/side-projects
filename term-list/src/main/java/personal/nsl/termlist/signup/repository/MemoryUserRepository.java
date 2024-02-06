package personal.nsl.termlist.signup.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import personal.nsl.termlist.signup.domain.User;
import personal.nsl.termlist.util.NLogger;

public class MemoryUserRepository implements UserRepository {
    private static final MemoryUserRepository instance = new MemoryUserRepository();
    private static final NLogger log = NLogger.getLogger();

    private final Map<String, User> repository = new ConcurrentHashMap<>();
    
    private MemoryUserRepository() {
    }
    
    public static UserRepository getInstance() {
        return instance;
    }

    @Override
    public boolean save(User user) {
        log.log("save: " + user, getClass());

        if (this.repository.containsKey(user.getId())) {
            this.repository.put(user.getId(), user);
            log.log("Succeeded to save", getClass());
            return true;
        }
        return false;
    }

    @Override
    public Optional<User> findUserById(String id) {
        Optional<User> found = Optional.ofNullable(this.repository.get(id));
        log.log("findUserById: " + found);
        return found;
    }

    @Override
    public List<User> findUsersByNameLike(String username) {
        List<User> users = this.repository.keySet().stream()
                .filter(key -> key.matches(String.format("*%s*", username)))
                .map(this.repository::get)
                .toList();
        log.log("findUsersByNameLike: " + users, getClass());
        return users;
    }

    @Override
    public User deleteUserById(String id) {
        User deleted = this.repository.remove(id);
        log.log("deleteUserById: " + deleted, getClass());
        return deleted;
    }

    @Override
    public User updateUserById(String id, User to) {
        User previousUpdate = this.repository.put(id, to);
        log.log("updateUserById: " + previousUpdate, getClass());
        return previousUpdate;
    }

}
