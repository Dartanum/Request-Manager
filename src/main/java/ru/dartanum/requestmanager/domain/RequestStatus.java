package ru.dartanum.requestmanager.domain;

public enum RequestStatus {
    CLOSE("Выполнена"),
    REJECTED("Отклонена"),
    IN_PROGRESS("Выполняется"),
    OPEN("Открыта");

    private final String displayName;

    RequestStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isNextStatus(RequestStatus nextStatus) {
        return switch (this) {
            case OPEN -> nextStatus == IN_PROGRESS || nextStatus == REJECTED;
            case IN_PROGRESS -> nextStatus == OPEN || nextStatus == CLOSE;
            case CLOSE, REJECTED -> false;
        };
    }
}
