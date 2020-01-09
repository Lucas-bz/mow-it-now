package fr.bouzidi.mowitnow.config;


import com.google.common.collect.Queues;
import fr.bouzidi.mowitnow.model.*;
import fr.bouzidi.mowitnow.parser.Parser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Queue;

@Configuration
public class ParserConfig {

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
