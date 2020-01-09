package fr.bouzidi.mowitnow.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.google.common.collect.Sets;
import fr.bouzidi.mowitnow.config.ParserConfig;
import fr.bouzidi.mowitnow.exceptions.FileFormatException;
import fr.bouzidi.mowitnow.model.*;
import fr.bouzidi.mowitnow.parser.Parser;
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

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = MowService.class)
@ContextConfiguration(classes = {ParserConfig.class, FileValidator.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class MowServiceTest {


    private String[] validArray = {"5 5", "1 2 N", "GAD"};

    private String[] invalidArray = {"5 X", "1 N", "XXX"};

    @Autowired
    private Parser<Lawn> lawnParser;

    @Autowired
    private Parser<Mower> mowerParser;

    @Autowired
    private Parser<Queue<Instruction>> instructionParser;

    @Autowired
    private FileValidator validator;

    @Autowired
    private MowService mowService;


    private static Lawn initial;
    private static Lawn expected;


    @Before
    public void setup() {
        Queue<Instruction> instructions = Queues.newArrayDeque();
        instructions.add(Instruction.ROTATE_LEFT);
        instructions.add(Instruction.FORWARD);
        instructions.add(Instruction.ROTATE_RIGHT);
        int initialValue = ParserConfig.getCounter();
        initial = Lawn.builder().height(5).width(5)
                .mowerSet(Sets.newHashSet(Mower.builder().id(initialValue).orientation(Orientation.NORTH)
                        .previous(Lists.newArrayList()).position(Position.of(1, 2))
                        .instructions(instructions)
                        .build()))
                .build();
        expected = Lawn.builder().height(5).width(5)
                .mowerSet(Sets.newHashSet(Mower.builder().id(initialValue).orientation(Orientation.NORTH)
                        .previous(Lists.newArrayList(Position.of(1, 2))).position(Position.of(0, 2))
                        .instructions(instructions)
                        .build()))
                .build();

    }


    @Test
    public void test_1_processEntry() {

        Queue<Instruction> instructions = Queues.newArrayDeque();
        instructions.add(Instruction.ROTATE_LEFT);
        instructions.add(Instruction.FORWARD);
        instructions.add(Instruction.ROTATE_RIGHT);
        Lawn result = mowService.processEntry(validArray);
        ReflectionAssert.assertReflectionEquals(initial, result);
    }

    @Test
    public void test_2_executeMowers() {
        Lawn result = mowService.executeMowers(initial);
        ReflectionAssert.assertReflectionEquals(expected, result);
    }

    @Test(expected = FileFormatException.class)
    public void test_fail_format() {
        try {
            mowService.processEntry(invalidArray);
        } catch (FileFormatException ffe) {
            assertEquals(3, ffe.getMessages().size());
            throw ffe;
        }

    }
}