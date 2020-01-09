package fr.bouzidi.mowitnow.model;


import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.unitils.reflectionassert.ReflectionAssert;

import java.util.List;


@RunWith(SpringRunner.class)
public class MowerTest {

    private Mower mower;

    @Before
    public void init() {
        mower = Mower.builder().orientation(Orientation.NORTH).position(Position.of(0, 0)).build();
    }

    @Test
    public void test_advance() {
        mower.advance();
        List<Position> previous = Lists.newArrayList();
        previous.add(Position.of(0, 0));
        Mower expected = Mower.builder().orientation(Orientation.NORTH).position(Position.of(0, 1)).previous(previous).build();

        ReflectionAssert.assertReflectionEquals(expected, mower);


    }

    @Test
    public void test_rotateRight() {
        mower.rotateRight();

        Mower expected = Mower.builder().orientation(Orientation.EAST).position(Position.of(0, 0)).build();

        ReflectionAssert.assertReflectionEquals(expected, mower);
    }

    @Test
    public void test_rotateLeft() {
        mower.rotateLeft();

        Mower expected = Mower.builder().orientation(Orientation.WEST).position(Position.of(0, 0)).build();

        ReflectionAssert.assertReflectionEquals(expected, mower);
    }
}