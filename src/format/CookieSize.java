package format;

import java.util.regex.Pattern;

public enum CookieSize {
    BIGSIZE(2.0), SMALLSIZE(1.5);

    private static final Pattern SIZE_PATTERN = Pattern.compile("^(BIGSIZE|SMALLSIZE)$", Pattern.CASE_INSENSITIVE);

    private final double value;

    CookieSize(double value) {
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    public static format.CookieSize fromString(String sizeStr) {
        if (SIZE_PATTERN.matcher(sizeStr).matches()) {
            switch (sizeStr.toUpperCase()) {
                case "BIGSIZE":
                    return BIGSIZE;
                case "SMALLSIZE":
                    return SMALLSIZE;
                default:
                    throw new IllegalArgumentException("Kích thước cookie không hợp lệ");
            }
        } else {
            throw new IllegalArgumentException("Chuỗi kích thước không khớp với bất kỳ kích thước cookie nào được biết đến.");
        }
    }
}

