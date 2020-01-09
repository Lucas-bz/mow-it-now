/*
 * Copyright (c) 2020.
 * Instruction.java created by farouk
 */

package fr.bouzidi.mowitnow.model;


import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.Arrays;

/**
 * Enum of possible mower instructions
 */

@AllArgsConstructor
@ToString
public enum Instruction {

    ROTATE_RIGHT('D') {
        @Override
        public void executer(final Mower mower) {
            mower.rotateRight();
        }
    },

    ROTATE_LEFT('G') {
        @Override
        public void executer(final Mower mower) {
            mower.rotateLeft();
        }
    },

    FORWARD('A') {
        @Override
        public void executer(final Mower mower) {
            mower.advance();
        }
    };

    private char inner;

    public abstract void executer(final Mower mower);

    public static Instruction fromSymbol(char symbol) {
        return Arrays.stream(Instruction.values()).filter(instruction -> instruction.inner == symbol).findAny().orElseGet(() -> Instruction.FORWARD);
    }
}
