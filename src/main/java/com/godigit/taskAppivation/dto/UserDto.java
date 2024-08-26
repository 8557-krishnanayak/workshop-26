package com.godigit.taskAppivation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserDto {
    private Long id;
    private String username;

    @JsonIgnoreProperties
    private String password;
    private String email;

    @Builder.Default
    private List<TaskDto> tasks = new ArrayList<>();
}
