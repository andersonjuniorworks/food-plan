package com.andersonjunior.foodplan.service.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.andersonjunior.foodplan.domain.models.User;
import com.andersonjunior.foodplan.domain.repositories.UserRepository;
import com.andersonjunior.foodplan.service.exceptions.DataIntegrityException;
import com.andersonjunior.foodplan.service.exceptions.ObjectNotFoundException;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;
    
    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public List<User> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable).getContent();
    }

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado na base de dados"));
    }

    public User findByEmail(String email) {
        Optional<User> user = userRepository.findByEmailIgnoreCase(email);
        return user.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado na base de dados"));
    }

    public List<User> findByName(String name) {
        return userRepository.findByNameContainsIgnoreCase(name);
    }

    @Transactional
    public User insert(User user) {
        user.setId(null);
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    public User update(User user) {
        User newUser = findById(user.getId());
        updateData(newUser, user);
        return userRepository.save(newUser);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        try {
            userRepository.deleteById(id);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Não é possível excluir este usuário!");
        }
    }

    @Transactional
    private User updatePassword(User user) {
        
        User oldUser = findById(user.getId());

        if(!user.getPassword().equals(oldUser.getPassword())) {
            return userRepository.save(user);
        } else {
            throw new DataIntegrityException("A nova senha não pode ser igual a anterior!");
        }

    }

    public Boolean login(String email, String password) {
        
        boolean valid = false;

        Optional<User> user = userRepository.findByEmailIgnoreCase(email);

        if(user.isEmpty()) {
            throw new DataIntegrityException("Os valores não podem ser vazios");
        } 

        valid = encoder.matches(password, user.get().getPassword());

        return valid;

    }

    private void updateData(User newUser, User user) {
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setProfile(user.getProfile());
    }

}
