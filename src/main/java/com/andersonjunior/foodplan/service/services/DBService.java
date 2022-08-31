package com.andersonjunior.foodplan.service.services;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.andersonjunior.foodplan.domain.enums.Gender;
import com.andersonjunior.foodplan.domain.enums.Intensity;
import com.andersonjunior.foodplan.domain.enums.Profile;
import com.andersonjunior.foodplan.domain.enums.Scheme;
import com.andersonjunior.foodplan.domain.models.FoodGroup;
import com.andersonjunior.foodplan.domain.models.FoodPlan;
import com.andersonjunior.foodplan.domain.models.Student;
import com.andersonjunior.foodplan.domain.models.Training;
import com.andersonjunior.foodplan.domain.models.TrainingGroup;
import com.andersonjunior.foodplan.domain.models.User;
import com.andersonjunior.foodplan.domain.repositories.FoodGroupRepository;
import com.andersonjunior.foodplan.domain.repositories.FoodPlanRepository;
import com.andersonjunior.foodplan.domain.repositories.StudentRepository;
import com.andersonjunior.foodplan.domain.repositories.TrainingGroupRepository;
import com.andersonjunior.foodplan.domain.repositories.TrainingRepository;
import com.andersonjunior.foodplan.domain.repositories.UserRepository;

@Service
public class DBService {
    
    private final UserRepository userRepository;
    private final FoodGroupRepository foodGroupRepository;
    private final FoodPlanRepository foodPlanRepository;
    private final TrainingGroupRepository trainingGroupRepository;
    private final TrainingRepository trainingRepository;
    private final StudentRepository studentRepository;

    public DBService(UserRepository userRepository, FoodGroupRepository foodGroupRepository, FoodPlanRepository foodPlanRepository, TrainingGroupRepository trainingGroupRepository, TrainingRepository trainingRepository, StudentRepository studentRepository) {
        this.userRepository = userRepository;
        this.foodGroupRepository = foodGroupRepository;
        this.foodPlanRepository = foodPlanRepository;
        this.trainingGroupRepository = trainingGroupRepository;
        this.trainingRepository = trainingRepository;
        this.studentRepository = studentRepository;
    }

    public void instantiateTestDatabase() throws ParseException {
        
        User user1 = new User(null, "ADMINISTRADOR", "andersonjunior.tech@gmail.com", new BCryptPasswordEncoder().encode("123"), Profile.ADMINISTRATOR, null, null);
        userRepository.saveAll(Arrays.asList(user1));

        FoodGroup fg1 = new FoodGroup(null, "DOM");
        FoodGroup fg2 = new FoodGroup(null, "SEG");
        FoodGroup fg3 = new FoodGroup(null, "TER");
        FoodGroup fg4 = new FoodGroup(null, "QUA");
        FoodGroup fg5 = new FoodGroup(null, "QUI");
        FoodGroup fg6 = new FoodGroup(null, "SEX");
        FoodGroup fg7 = new FoodGroup(null, "SÁB");
        foodGroupRepository.saveAll(Arrays.asList(fg1, fg2, fg3, fg4, fg5, fg6, fg7));
                      
        FoodPlan fp1 = new FoodPlan(null, fg1, "Em jejum", "07:00", "Limão (Comum, galego, etc.) (Unidade: 1)", "Espremer 1 limão em um copo de água", null, null);
        FoodPlan fp2 = new FoodPlan(null, fg1, "Café da manhã", "07:30", "Tapioca de goma (Grama: 50)", "Preparar com ovo", null, null);       
        foodPlanRepository.saveAll(Arrays.asList(fp1, fp2));

        TrainingGroup tg1 = new TrainingGroup(null, "TREINO A - (COSTAS E BÍCEPS)");
        TrainingGroup tg2 = new TrainingGroup(null, "TREINO B - (QUADRÍCEPS E PATURRILHA)");

        Training t1 = new Training(null, "PUXADA FRONTAL ABERTA", "3", "12", "30 segundos", null, null, null);
        Training t2 = new Training(null, "PUXADA FRONTAL TRIÂNGULO", "3", "10", "30 segundos", null, null, null);        
        
        tg1.setTrainings(Arrays.asList(t1, t2));

        t1.setTrainingGroups(Arrays.asList(tg1));
        t2.setTrainingGroups(Arrays.asList(tg1));

        trainingGroupRepository.saveAll(Arrays.asList(tg1, tg2));
        trainingRepository.saveAll(Arrays.asList(t1, t2));

        Student st1 = new Student(null, user1, 24, LocalDateTime.now(), Gender.MALE, Intensity.SOFT, Scheme.QUARTERLY, "Perda de peso e definição muscular");
        studentRepository.saveAll(Arrays.asList(st1));

    }

}
