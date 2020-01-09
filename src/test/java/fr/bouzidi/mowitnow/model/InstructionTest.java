package fr.bouzidi.mowitnow.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InstructionTest {


    Mower mower = Mower.builder().orientation(Orientation.NORTH).position(Position.of(0,0)).build();

    @Test
    public void executer() {
        Instruction instruction = Instruction.FORWARD ;
        instruction.executer(mower);
        assertEquals(Position.of(0, 1), mower.getPosition());
        assertEquals(Orientation.NORTH, mower.getOrientation());

        instruction = Instruction.ROTATE_RIGHT;
        instruction.executer(mower);
        assertEquals(Position.of(0, 1), mower.getPosition());
        assertEquals(Orientation.EAST, mower.getOrientation());

        instruction = Instruction.FORWARD;
        instruction.executer(mower);
        assertEquals(Position.of(1, 1), mower.getPosition());
        assertEquals(Orientation.EAST, mower.getOrientation());

        instruction = Instruction.ROTATE_LEFT;
        instruction.executer(mower);
        instruction = Instruction.ROTATE_LEFT;
        instruction.executer(mower);
        assertEquals(Position.of(1, 1), mower.getPosition());
        assertEquals(Orientation.WEST, mower.getOrientation());

    }

    @Test
    public void fromSymbol() {
        assertEquals(Instruction.FORWARD, Instruction.fromSymbol('A'));
        assertEquals(Instruction.ROTATE_LEFT, Instruction.fromSymbol('G'));
        assertEquals(Instruction.ROTATE_RIGHT, Instruction.fromSymbol('D'));


        // When unknown Symbol, assert instruction is forward
        assertEquals(Instruction.FORWARD, Instruction.fromSymbol('X'));
    }
}