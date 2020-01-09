/*
 * Copyright (c) 2020.
 * ParserConfig.java created by farouk
 */

package fr.bouzidi.mowitnow.config;


import com.google.common.collect.Queues;
import fr.bouzidi.mowitnow.model.*;
import fr.bouzidi.mowitnow.parser.Parser;
import lombok.Data;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Queue;


/**
 * ParserConfig expose 3 beans :
 * Parser<Mower> Bean is able to convert a string in to a Mower
 * Parser<Lawn> Bean is able to convert a string in to a Lawn
 * Parser<Queue<Instruction>> Bean is able to convert a string in to a Queue<Instruction>
 */



@Configuration
@Data
public class ParserConfig {

    @Getter
    private static int counter = 0;

    @Bean
    public Parser<Lawn> lawnParser() {
        return row -> {
            String[] splitted = row.split(" ");
            return Lawn.builder().width(Integer.valueOf(splitted[0])).height(Integer.valueOf(splitted[1])).build();
        };
    }

    @Bean
    public Parser<Mower> mowerParser() {
        return row -> {
            String[] splitted = row.split(" ");
            return Mower.builder()
                    .id(getNextId())
                    .position(Position.of(Integer.valueOf(splitted[0]), Integer.valueOf(splitted[1])))
                    .orientation(Orientation.fromSymbol(splitted[2])).build();
        };
    }

    @Bean
    public Parser<Queue<Instruction>> instructionParser() {
        return row -> {
            Queue<Instruction> instructions = Queues.newArrayDeque();
            row.chars().forEach(c -> instructions.add(Instruction.fromSymbol((char) c)));
            return instructions;
        };
    }

    private static int getNextId(){
        return counter++;
    }

}
