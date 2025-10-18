package ru.moscowzoo.clinic;

/**
 * Result of veterinary inspection.
 */
public final class InspectionReport {
    private final boolean accepted;
    private final String message;

    private InspectionReport(boolean accepted, String message) {
        this.accepted = accepted;
        this.message = message;
    }

    public static InspectionReport accepted(String message) {
        return new InspectionReport(true, message);
    }

    public static InspectionReport rejected(String message) {
        return new InspectionReport(false, message);
    }

    public boolean isAccepted() {
        return accepted;
    }

    public String getMessage() {
        return message;
    }
}
