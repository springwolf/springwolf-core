package com.stavshamir.swagger4kafka.validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stavshamir.swagger4kafka.dtos.ValidationMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class PayloadValidator {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static ValidationMessage validate(Map<String, Object> payload, String payloadClassName) {
        Class<?> payloadClass;
        try {
            payloadClass = Class.forName(payloadClassName);
        } catch (ClassNotFoundException e) {
            return new ValidationMessage(false,"Could not validate");
        }

        try {
            objectMapper.convertValue(payload, payloadClass);
        } catch (IllegalArgumentException ex) {
            log.info(ex.getMessage());

            String message = formatErrorMessage(ex.getMessage());
            return new ValidationMessage(false, message);
        }

        return new ValidationMessage(true, "");
    }

    private static String formatErrorMessage(String errorMessage) {
        if (errorMessage.startsWith("Unrecognized field")) {
            return formatUnrecognizedFieldErrorMessage(errorMessage);
        } else if (errorMessage.startsWith("Cannot deserialize")) {
            return formatCannotDeserializeErrorMessage(errorMessage);
        }

        return errorMessage;
    }

    private static String formatUnrecognizedFieldErrorMessage(String errorMessage) {
        String patternString = "(.*?)(\\s\\(class.*)";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(errorMessage.replace("\n", ""));

        if (matcher.find()) {
            return matcher.group(1);
        }

        return errorMessage;
    }

    private static String formatCannotDeserializeErrorMessage(String errorMessage) {
        String patternString = "(.*?)(\\[Source.*\\[)(.*?)(]\\))";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(errorMessage.replace("\n", ""));

        if (matcher.find()) {
            return matcher.group(1) + matcher.group(3);
        }

        return errorMessage;
    }

}
