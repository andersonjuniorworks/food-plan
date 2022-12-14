package com.andersonjunior.foodplan.domain.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.andersonjunior.foodplan.domain.enums.Gender;
import com.andersonjunior.foodplan.domain.enums.Intensity;
import com.andersonjunior.foodplan.domain.enums.Scheme;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Student implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User access;

    private Integer age;
    private LocalDateTime birthDate;

    private Gender gender;
    private Intensity intensity;
    private Scheme scheme;

    private String objective;

    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateAt = LocalDateTime.now();
    }

    public Student(Long id, User access, Integer age, LocalDateTime birthDate, Gender gender, Intensity intensity,
            Scheme scheme, String objective) {
        this.id = id;
        this.access = access;
        this.age = age;
        this.birthDate = birthDate;
        this.gender = gender;
        this.intensity = intensity;
        this.scheme = scheme;
        this.objective = objective;
    }

}
