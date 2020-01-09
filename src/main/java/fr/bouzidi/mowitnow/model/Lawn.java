package fr.bouzidi.mowitnow.model;


import com.google.common.collect.Sets;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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


    public boolean isInGrid(Position position) {
        return position.getX() > width || position.getY() > height || position.getX() < 0 || position.getY() < 0;
    }

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
