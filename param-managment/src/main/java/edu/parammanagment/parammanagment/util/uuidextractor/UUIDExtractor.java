package edu.parammanagment.parammanagment.util.uuidextractor;

import lombok.experimental.UtilityClass;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class UUIDExtractor {

    private final String UUID_REGEX = "\\p{XDigit}{8}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{12}";

    public Optional<String> extractUUID(String ref){
        return findIdInURI(ref);
    }
    private Optional<String> findIdInURI(String ref){
        Pattern pairRegex = Pattern.compile(UUID_REGEX);
        Matcher matcher = pairRegex.matcher(ref);
        String entityUUID = null;
        while (matcher.find()) {
            entityUUID = matcher.group(0);
        }
        return Optional.ofNullable(entityUUID);
    }


}
