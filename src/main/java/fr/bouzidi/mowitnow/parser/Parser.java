package fr.bouzidi.mowitnow.parser;


public interface Parser<T> {

    T parse(String row);
}
