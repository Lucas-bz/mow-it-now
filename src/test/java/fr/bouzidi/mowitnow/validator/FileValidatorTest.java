package fr.bouzidi.mowitnow.validator;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.*;

@Slf4j
public class FileValidatorTest {


    private String[] validArray = {"5 5","1 2 N","GAGAGAGAA","3 3 E","AADAADADDA" } ;
    private String[] notValidArray = {"5 5","1 2 N","GAGADXGAA","3 A E","AADAADADDA" } ;
    private String[] emptyArray = {};

    private FileValidator validator = new FileValidator();



    @Test
    public void test_validate_isValid() {
        assertTrue(validator.validate(validArray).isValid());
    }


    @Test
    public void test_validate_isNotValid() {
        ValidationResult result = validator.validate(notValidArray) ;
        log.info("Rsult : {}", result);
        assertFalse(result.isValid());
        assertEquals(2, result.getMessages().size());
    }


    @Test
    public void test_validate_emptyFile() {
        ValidationResult result = validator.validate(emptyArray) ;
        assertFalse(result.isValid());
        assertThat(result.getMessages(), contains("File is empty !"));

    }
}