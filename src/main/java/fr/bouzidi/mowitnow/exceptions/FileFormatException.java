package fr.bouzidi.mowitnow.exceptions;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Builder
@Data
@ToString
public class FileFormatException extends RuntimeException {

    final transient List<String> messages;
}
