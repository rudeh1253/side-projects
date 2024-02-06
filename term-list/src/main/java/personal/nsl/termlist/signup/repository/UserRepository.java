package personal.nsl.termlist.signup.repository;

import java.util.List;
import java.util.Optional;

import personal.nsl.termlist.signup.domain.User;

public interface UserRepository {

    public boolean save(User user);
    
    public Optional<User> findUserById(String id);
    
    public List<User> findUsersByNameLike(String username);
    
    public User deleteUserById(String id);
    
    public User updateUserById(String id, User to);
}
