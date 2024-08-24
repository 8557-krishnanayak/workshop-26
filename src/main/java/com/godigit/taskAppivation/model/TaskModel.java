package com.godigit.taskAppivation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private String description;
    private LocalDate date;
    private String status;
    private String priority;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = CategoryModal.class)
    @JoinColumn(name = "category_id")
    private CategoryModal category;
}
