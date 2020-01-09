package fr.bouzidi.mowitnow.parser;

import com.google.common.collect.Queues;
import fr.bouzidi.mowitnow.config.ParserConfig;
import fr.bouzidi.mowitnow.model.*;
import fr.bouzidi.mowitnow.service.MowService;
import fr.bouzidi.mowitnow.validator.FileValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.unitils.reflectionassert.ReflectionAssert;

import java.util.Queue;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {ParserConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ParserTest {


    @Autowired
    private Parser<Mower> mowerParser;

    @Autowired
    private Parser<Lawn> lawnParser;

    @Autowired
    private Parser<Queue<Instruction>> instructionParser;




    @Test
    public void testValidMower(){
        String valid = "1 5 W";
        int id = ParserConfig.getCounter();
        Mower expected = Mower.builder().id(id).orientation(Orientation.WEST).position(Position.of(1, 5)).build();
        Mower actual = mowerParser.parse(valid);
        ReflectionAssert.assertReflectionEquals(expected, actual);
    }

    @Test
    public void testValidLawn(){
        String valid = "1 5";
        Lawn expected = Lawn.builder().height(5).width(1).build();
        Lawn actual = lawnParser.parse(valid);
        ReflectionAssert.assertReflectionEquals(expected, actual);
    }

    @Test
    public void testValidInstructions(){
        String valid = "AAAGD";
        Queue<Instruction> expected = Queues.newArrayDeque();
        expected.add(Instruction.FORWARD);
        expected.add(Instruction.FORWARD);
        expected.add(Instruction.FORWARD);
        expected.add(Instruction.ROTATE_LEFT);
        expected.add(Instruction.ROTATE_RIGHT);
        Queue<Instruction> actual = instructionParser.parse(valid);
        ReflectionAssert.assertReflectionEquals(expected, actual);
    }


}
