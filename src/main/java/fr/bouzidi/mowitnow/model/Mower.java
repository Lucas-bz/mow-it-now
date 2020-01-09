/*
 * Copyright (c) 2020.
 * Mower.java created by farouk
 */

package fr.bouzidi.mowitnow.model;


import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;




@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Data
public class Mower implements Serializable {


    @EqualsAndHashCode.Exclude private int id;
    private Position position;
    @Builder.Default
    private List<Position> previous = Lists.newArrayList();

    private Orientation orientation;

    @Builder.Default
    private Queue<Instruction> instructions = Queues.newArrayDeque();


    public void advance() {
        Position current = this.position;
        this.previous.add(this.position);
        this.position = orientation.getNext(current);

    }

    public void rotateRight() {
        this.orientation = orientation.rotateRight();
    }

    public void rotateLeft() {
        this.orientation = orientation.rotateLeft();
    }


    /**
     * A static factory to have a deep clone of a given Mower object
     * @param input given Mower object
     * @return a deep clone of Mower
     */

    public static Mower copyFactory(Mower input) {
        if (input == null) {
            return null;
        }
        return Mower.builder().id(input.id).position(Position.copyFactory(input.position))
                .previous(input.previous == null ? null : input.previous.stream().map(Position::copyFactory).collect(Collectors.toList()))
                .instructions(input.instructions).orientation(Orientation.fromSymbol(String.valueOf(input.orientation.getInner()))).build();
    }

}
