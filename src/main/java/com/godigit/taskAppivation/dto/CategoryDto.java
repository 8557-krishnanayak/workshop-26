package com.godigit.taskAppivation.dto;

import com.godigit.taskAppivation.model.TaskModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDto {
    private Long id;
    private String name;


//    private List<TaskModel> tasks;
}
