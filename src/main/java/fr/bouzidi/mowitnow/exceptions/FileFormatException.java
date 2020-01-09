/*
 * Copyright (c) 2020.
 * FileFormatException.java created by farouk
 */

package fr.bouzidi.mowitnow.exceptions;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;


/**
 * Exception class to be thrown when given a wrong file format
 */
@Builder
@Data
@ToString
public class FileFormatException extends RuntimeException {

    final transient List<String> messages;
}
