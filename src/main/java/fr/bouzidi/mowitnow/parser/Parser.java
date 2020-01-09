/*
 * Copyright (c) 2020.
 * Parser.java created by farouk
 */

package fr.bouzidi.mowitnow.parser;


/**
 * Funtional Interface to Parse a String to a T Type
 * @param <T>
 */
public interface Parser<T> {

    T parse(String row);
}
