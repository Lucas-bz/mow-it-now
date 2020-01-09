package fr.bouzidi.mowitnow.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrientationTest {

    Position position = Position.of(2, 3);

    @Test
    public void getNext() {
        Orientation orientation = Orientation.WEST;
        assertEquals(Position.of(1, 3), orientation.getNext(position));
        orientation = Orientation.NORTH;
        assertEquals(Position.of(2, 4), orientation.getNext(position));
        orientation = Orientation.SOUTH;
        assertEquals(Position.of(2, 2), orientation.getNext(position));
        orientation = Orientation.EAST;
        assertEquals(Position.of(3, 3), orientation.getNext(position));
    }

    @Test
    public void rotateRight() {
        Orientation orientation = Orientation.WEST;
        assertEquals(Orientation.NORTH , orientation.rotateRight());
    }

    @Test
    public void rotateLeft() {
        Orientation orientation = Orientation.SOUTH;
        assertEquals(Orientation.EAST , orientation.rotateLeft());
    }

    @Test
    public void fromSymbol() {
        assertEquals(Orientation.WEST, Orientation.fromSymbol("W"));
        assertEquals(Orientation.NORTH, Orientation.fromSymbol("N"));
        assertEquals(Orientation.SOUTH, Orientation.fromSymbol("S"));
        assertEquals(Orientation.EAST, Orientation.fromSymbol("E"));

        // When unknown Symbol, assert orientation is North
        assertEquals(Orientation.NORTH, Orientation.fromSymbol("OPL"));

    }
}