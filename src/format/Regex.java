package format;

import java.util.regex.Pattern;

public interface Regex {
    Pattern QUANTITY_PATTERN = Pattern.compile("^(1[0-9]|20?|[2-9])$");
    Pattern DATE_PATTERN = Pattern.compile("^(19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$");

    public void setQuantityFromString(String quantityStr);
    public void setDateFromString(String dateString);
}
