package fr.bouzidi.mowitnow.validator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@AllArgsConstructor
@ToString
@Getter
public class ValidationResult {

    private boolean isValid;
    private List<String> messages;
}
