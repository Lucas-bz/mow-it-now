/*
 * Copyright (c) 2020.
 * MowItNowResponse.java created by farouk
 */

package fr.bouzidi.mowitnow.controller;


import fr.bouzidi.mowitnow.model.Lawn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Data to be sent by the controller
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MowItNowResponse {

    private Lawn initial;
    private Lawn last;
}
