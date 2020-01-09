package fr.bouzidi.mowitnow.service;


import com.google.common.collect.Sets;
import fr.bouzidi.mowitnow.exceptions.FileFormatException;
import fr.bouzidi.mowitnow.model.Instruction;
import fr.bouzidi.mowitnow.model.Lawn;
import fr.bouzidi.mowitnow.model.Mower;
import fr.bouzidi.mowitnow.model.Position;
import fr.bouzidi.mowitnow.parser.Parser;
import fr.bouzidi.mowitnow.validator.FileValidator;
import fr.bouzidi.mowitnow.validator.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.Set;

@Service
public class MowService {


    @Autowired
    private Parser<Lawn> lawnParser;

    @Autowired
    private Parser<Mower> mowerParser;

    @Autowired
    private Parser<Queue<Instruction>> instructionParser;

    @Autowired
    private FileValidator validator;


    public Lawn processEntry(String[] rows) {
        ValidationResult validationResult = validator.validate(rows);
        if (validationResult.isValid()) {
            Lawn lawn = lawnParser.parse(rows[0]);
            int i = 1;
            Set<Mower> mowers = Sets.newHashSet();
            while (i < rows.length) {
                Mower mower = mowerParser.parse(rows[i++]);
                mower.setInstructions(instructionParser.parse(rows[i++]));
                mowers.add(mower);
            }
            lawn.setMowerSet(mowers);
            return lawn;
        } else {
            throw FileFormatException.builder().messages(validationResult.getMessages()).build();
        }

    }

    public Lawn executeMowers(Lawn initial) {
        Lawn lawn = Lawn.copyFactory(initial);
        for (Mower mower : lawn.getMowerSet()) {
            mower.getInstructions().stream().forEach(instruction -> {
                boolean validInstruction = true;
                if (instruction == Instruction.FORWARD) {
                    Position position = mower.getOrientation().getNext(mower.getPosition());
                    if (lawn.isInGrid(position)) {
                        validInstruction = false;
                    }
                }
                if (validInstruction) {
                    instruction.executer(mower);
                }

            });
        }
        return lawn;
    }
}
