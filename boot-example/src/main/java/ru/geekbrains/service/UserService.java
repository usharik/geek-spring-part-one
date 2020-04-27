package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.persist.User;
import ru.geekbrains.persist.UserRepository;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(UserRepr userRepr) {
        User user = new User();
        user.setId(userRepr.getId());
        user.setUsername(userRepr.getUsername());
        user.setPassword(userRepr.getPassword());
        user.setRoles(userRepr.getRoles());
        userRepository.save(user);
    }

    public List<UserRepr> findAll() {
        return userRepository.findAll().stream()
                .map(UserRepr::new)
                .collect(Collectors.toList());
    }

    public Optional<UserRepr> findById(Long id) {
        return userRepository.findById(id)
                .map(UserRepr::new);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
