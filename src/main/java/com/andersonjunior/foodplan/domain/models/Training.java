package com.andersonjunior.foodplan.domain.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Training implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "trainings_groups", joinColumns = @JoinColumn(name = "training_id"), inverseJoinColumns = @JoinColumn(name = "training_group_id"))
    private List<TrainingGroup> trainingGroups;

    private String description;
    private String series;
    private String repeat;
    private String repose;
    private String cadence;
    private String weight;
    private String method;
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

    public Training(Long id, String description, String series, String repeat,
            String repose, String cadence, String weight, String method) {
        this.id = id;
        this.description = description;
        this.series = series;
        this.repeat = repeat;
        this.repose = repose;
        this.cadence = cadence;
        this.weight = weight;
        this.method = method;
    }

}
