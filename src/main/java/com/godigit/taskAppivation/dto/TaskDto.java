package com.godigit.taskAppivation.dto;

import com.godigit.taskAppivation.model.CategoryModal;
import com.godigit.taskAppivation.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TaskDto {

    private Long id;
    private String title;
    private String description;
    private LocalDate date;
    private String status;
    private String priority;

    private CategoryModal category;
}
