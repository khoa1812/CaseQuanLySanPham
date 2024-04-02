package format;

import java.util.regex.Pattern;

public enum Sales {
    SALE10(0.9), SALE15(0.85), SALE20(0.8);
    private static final Pattern SALES_PATTERN = Pattern.compile("^(SALE10|SALE15|SALE20)$");
    private final double percent;

    private Sales(double percent) {
        this.percent = percent;
    }

    public double getPercent() {
        return this.percent;
    }

    public static format.Sales fromString(String saleStr) {
        if (SALES_PATTERN.matcher(saleStr).matches()) {
            switch (saleStr) {
                case "SALE10":
                    return SALE10;
                case "SALE15":
                    return SALE15;
                case "SALE20":
                    return SALE20;
                default:
                    throw new IllegalArgumentException("Loại giảm giá không hợp lệ");
            }
        } else {
            throw new IllegalArgumentException("Chuỗi giảm giá không khớp với bất kỳ loại giảm giá nào được biết đến.");
        }
    }
}

