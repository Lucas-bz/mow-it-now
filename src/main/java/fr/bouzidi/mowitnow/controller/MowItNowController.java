/*
 * Copyright (c) 2020.
 * MowItNowController.java created by farouk
 */

package fr.bouzidi.mowitnow.controller;


import fr.bouzidi.mowitnow.exceptions.FileFormatException;
import fr.bouzidi.mowitnow.model.Lawn;
import fr.bouzidi.mowitnow.service.FileStorageService;
import fr.bouzidi.mowitnow.service.MowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * MowItController expose the REST entryPoint
 */

@Slf4j
@RestController
public class MowItNowController {


    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private MowService mowService;


    @PostMapping("/uploadFile")
    public MowItNowResponse uploadFile(@RequestParam("file") MultipartFile file) {

        String[] rows = fileStorageService.getFileContent(file);
        Lawn initial = mowService.processEntry(rows);
        Lawn last = mowService.executeMowers(initial);
        return MowItNowResponse.builder().initial(initial).last(last).build();
    }


    @ExceptionHandler(FileFormatException.class)
    public ResponseEntity<Object> handleException(FileFormatException ex) {
        return buildResponseEntity(ApiError.builder().messages(ex.getMessages()).status(HttpStatus.BAD_REQUEST).build());

    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
