package org.shorts.battle;

public enum Weather {
    NONE(
        false,
        "This message for activating normal weather should never be seen.",
        "This message for deactivating normal weather should never be seen.", null),
    RAIN(false, "It started to rain!", "The rain stopped.", null),
    SUN(false, "The sunlight turned harsh!", "The harsh sunlight faded.", null),
    SAND(false, "A sandstorm kicked up!", "The sandstorm subsided.", "{} is buffeted by the sandstorm!"),
    HAIL(false, "It started to hail!", "The hail stopped.", "{} is buffeted by the hail!"),
    SNOW(false, "It started to snow!", "The snow stopped.", null),
    EXTREME_RAIN(true, "A heavy rain began to fall!", "The heavy rain has lifted!", null),
    EXTREME_SUN(true, "The sunlight turned extremely harsh!", "The harsh sunlight faded.", null),
    EXTREME_WIND(
        true,
        "Mysterious strong winds are protecting Flying-type Pokémon!",
        "The mysterious strong winds have dissipated!",
        null);

    private final boolean extreme;
    private final String activationMessage;
    private final String deactivationMessage;
    private final String damageMessage;

    public boolean isExtreme() {
        return extreme;
    }

    public String getActivationMessage() {
        return activationMessage;
    }

    public String getDeactivationMessage() {
        return deactivationMessage;
    }

    public String getDamageMessage() {
        return damageMessage;
    }

    Weather(boolean extreme, String activationMessage, String deactivationMessage, String damageMessage) {
        this.extreme = extreme;
        this.activationMessage = activationMessage;
        this.deactivationMessage = deactivationMessage;
        this.damageMessage = damageMessage;
    }

    public static final int DEFAULT_WEATHER_DURATION = 5;

    public static final int EXTENDED_WEATHER_DURATION = 8;
    public static final int INFINITE_WEATHER_DURATION = -1;
}
