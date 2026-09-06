package net.kaupenjoe.tutorialmod.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum CapybaraVariant {
    BROWN(0),
    SNOW(1);

    private static final CapybaraVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(
            CapybaraVariant::getId)).toArray(CapybaraVariant[]::new);
    private final int id;

    CapybaraVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static CapybaraVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
