/*
 * Copyright (c) 2020.
 * ApiError.java created by farouk
 */

package fr.bouzidi.mowitnow.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;



@Builder
@Data
public class ApiError {


    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
    private List<String> messages;
}
