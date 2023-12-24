package fr.teama.structural.enums;

public enum NoteDurationEnum {
    R(16),
    BL_P(12),
    BL(8),
    N_P(6),
    N(4),
    C_P(3),
    C(2),
    D_C(1);

    private int duration;

    NoteDurationEnum(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }
}
