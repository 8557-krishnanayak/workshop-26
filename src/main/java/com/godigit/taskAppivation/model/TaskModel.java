package com.godigit.taskAppivation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor

@Data
@Entity
@Table(name = "tasks")
public class TaskModel {

    @Id
    @GeneratedValue
    private Long id;
    private String title;

    @Builder.Default
    private String description = "";
    private LocalDate date;
    private String status;
    private String priority;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = CategoryModal.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private CategoryModal category;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = UserModel.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserModel user;
}
