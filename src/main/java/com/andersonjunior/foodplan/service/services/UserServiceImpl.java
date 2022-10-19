package com.andersonjunior.foodplan.service.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andersonjunior.foodplan.domain.models.Role;
import com.andersonjunior.foodplan.domain.models.User;
import com.andersonjunior.foodplan.domain.repositories.RoleRepository;
import com.andersonjunior.foodplan.domain.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User saveUser(User user) {
        log.info("Salvando um novo usuário {} no banco de dados", user.getName());
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Salvando uma nova role {} no banco de dados", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        log.info("Vinculando a role {} com o usuário {}", roleName, getUserByEmail(email).getName());
        User user = userRepository.findByEmail(email);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUserByEmail(String email) {
        log.info("Buscando usuário pelo email {}", email);
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getByName(String name) {
        log.info("Buscando usuário pelo nome {}", name);
        return userRepository.findByNameContainsIgnoreCase(name);
    }

    @Override
    public List<User> getUsers() {
        log.info("Listando todos os usuários");
        return userRepository.findAll();
    }
    
}
