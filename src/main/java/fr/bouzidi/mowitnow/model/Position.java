package fr.bouzidi.mowitnow.model;


import lombok.AllArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Value
@AllArgsConstructor(staticName = "of")
public class Position implements Serializable {

    int x;
    int y;



    public static Position copyFactory(Position input){
        return input == null ? null : Position.of(input.x, input.y);
    }
}
