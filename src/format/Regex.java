package format;

import java.util.regex.Pattern;

public interface Regex {
    Pattern QUANTITY_PATTERN = Pattern.compile("^([1-9]|1[0-9]|20)$");

}
