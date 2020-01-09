/*
 * Copyright (c) 2020.
 * FileValidator.java created by farouk
 */

package fr.bouzidi.mowitnow.validator;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
public class FileValidator implements Validator<String[]> {

    private static String lawnPattern = "^\\d+ \\d+$";
    private static String instructionsPattern = "[GDA]*";
    private static String mowerPattern = "^\\d+ \\d+ [N|E|W|S]$";


    @Override
    public ValidationResult validate(String[] rows) {

        if (rows.length == 0) {
            ValidationResult.ValidationResultBuilder builder = ValidationResult.builder();
            builder.isValid(false);
            builder.messages(Lists.newArrayList("File is empty !"));
            return builder.build();
        }
        List<String> messages = Lists.newArrayList();
        Pattern pattern;
        int i = 0;
        while (i < rows.length) {
            if (i == 0) {
                pattern = Pattern.compile(lawnPattern);
                if (!pattern.matcher(rows[i]).matches()) {
                    messages.add("Can't initialize Lawn from entry " + rows[i]);
                }
            } else if (i % 2 != 0) {
                pattern = Pattern.compile(mowerPattern);
                if (!pattern.matcher(rows[i]).matches()) {
                    messages.add("Can't initialize mower from entry " + rows[i]);
                }
            } else if (i % 2 == 0) {
                pattern = Pattern.compile(instructionsPattern);
                if (!pattern.matcher(rows[i]).matches()) {
                    messages.add("Can't initialize Instructions from entry " + rows[i]);
                }
            }

            i++;
        }
        return ValidationResult.builder().messages(messages).isValid(messages.isEmpty()).build();
    }
}
