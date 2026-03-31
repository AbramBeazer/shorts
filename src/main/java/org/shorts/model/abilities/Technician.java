package org.shorts.model.abilities;

public class Technician extends Ability {

    public static final double MULTIPLIER = 1.5;
    public static final int BASE_POWER_THRESHOLD = 60;

    private Technician() {
        super("Technician");
    }

    public static final Technician TECHNICIAN = new Technician();
}
