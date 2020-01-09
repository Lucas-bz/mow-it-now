/*
 * Copyright (c) 2020.
 * Lawn.java created by farouk
 */

package fr.bouzidi.mowitnow.model;


import com.google.common.collect.Sets;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * Lawn object :
 * A Lawn is defined by width, height and a set of Mowers
 */

@Builder(toBuilder = true)
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Data
public class Lawn implements Serializable {

    private int width;

    private int height;

    @Builder.Default
    private Set<Mower> mowerSet = Sets.newHashSet();


    /**
     * Verify that a position is in the Lawn (Grid)
     * @param position given position to be verified
     * @return boolean
     */
    public boolean isInGrid(Position position) {
        return position.getX() > width || position.getY() > height || position.getX() < 0 || position.getY() < 0;
    }

    /**
     * A static factory to have a deep clone of a given Lawn object
     * @param input given Lawn object
     * @return a deep clone of Lawn
     */
    public static Lawn copyFactory(Lawn input) {

        if (input == null) {
            return null;
        }

        Set<Mower> clonedMowers = new HashSet<>();
        for (Mower mower : input.getMowerSet()) {
            clonedMowers.add(Mower.copyFactory(mower));
        }
        return input.toBuilder().mowerSet(clonedMowers).build();
    }


}
