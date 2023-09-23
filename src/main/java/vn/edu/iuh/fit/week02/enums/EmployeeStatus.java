package vn.edu.iuh.fit.week02.enums;

public enum EmployeeStatus {
    WORKING(1, "Đang làm việc"),
    ON_LEAVE(0, "Tạm nghỉ"),
    TERMINATED(-1, "Đuổi việc");
    private final int code;
    private final String description;

    EmployeeStatus(int code, String description) {
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
