package fr.teama.structural;

public enum NoteDurationEnum {
    SEMIBREVE_DOT(12),
    SEMIBREVE(8),
    MINIM_DOT(6),
    MINIM(4),
    CROTCHET_DOT(3),
    CROTCHET(2),
    QUAVER(1);

    private int duration;

    NoteDurationEnum(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }
}
