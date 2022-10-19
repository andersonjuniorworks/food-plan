package com.andersonjunior.foodplan.service.services;

import java.util.List;

import com.andersonjunior.foodplan.domain.models.Role;
import com.andersonjunior.foodplan.domain.models.User;

public interface UserService {

   User saveUser(User user);
   Role saveRole(Role role);
   void addRoleToUser(String email, String roleName);
   User getUserByEmail(String email);
   List<User> getByName(String name);
   List<User> getUsers();
   
}
