package com.andersonjunior.foodplan.service.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.andersonjunior.foodplan.domain.enums.Profile;
import com.andersonjunior.foodplan.domain.models.FoodGroup;
import com.andersonjunior.foodplan.domain.models.FoodPlan;
import com.andersonjunior.foodplan.domain.models.User;
import com.andersonjunior.foodplan.domain.repositories.FoodGroupRepository;
import com.andersonjunior.foodplan.domain.repositories.FoodPlanRepository;
import com.andersonjunior.foodplan.domain.repositories.UserRepository;

@Service
public class DBService {
    
    private final UserRepository userRepository;
    private final FoodGroupRepository foodGroupRepository;
    private final FoodPlanRepository foodPlanRepository;

    public DBService(UserRepository userRepository, FoodGroupRepository foodGroupRepository, FoodPlanRepository foodPlanRepository) {
        this.userRepository = userRepository;
        this.foodGroupRepository = foodGroupRepository;
        this.foodPlanRepository = foodPlanRepository;
    }

    public void instantiateTestDatabase() throws ParseException {
        
        User user1 = new User(null, "ADMINISTRADOR", "andersonjunior.tech@gmail.com", "123", Profile.ADMINISTRATOR, null, null);
        userRepository.saveAll(Arrays.asList(user1));

        FoodGroup fg1 = new FoodGroup(null, "DOM");
        FoodGroup fg2 = new FoodGroup(null, "SEG");
        FoodGroup fg3 = new FoodGroup(null, "TER");
        FoodGroup fg4 = new FoodGroup(null, "QUA");
        FoodGroup fg5 = new FoodGroup(null, "QUI");
        FoodGroup fg6 = new FoodGroup(null, "SEX");
        FoodGroup fg7 = new FoodGroup(null, "SÁB");
                      
        FoodPlan fp1 = new FoodPlan(null, fg1, "Em jejum", "07:00", "Limão (Comum, galego, etc.) (Unidade: 1)", "Espremer 1 limão em um copo de água", null, null);
        FoodPlan fp2 = new FoodPlan(null, fg1, "Café da manhã", "07:30", "Tapioca de goma (Grama: 50)", "Preparar com ovo", null, null);       
                
        foodGroupRepository.saveAll(Arrays.asList(fg1, fg2, fg3, fg4, fg5, fg6, fg7));
        foodPlanRepository.saveAll(Arrays.asList(fp1, fp2));

    }

}
