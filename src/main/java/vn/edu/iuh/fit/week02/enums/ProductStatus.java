package vn.edu.iuh.fit.week02.enums;

public enum ProductStatus {
    ACTIVE(1, "Đang kinh doanh"),
    PAUSED(0, "Tạm ngưng"),
    INACTIVE(-1, "Không kinh doanh");

    private final int code;
    private final String description;

    ProductStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
