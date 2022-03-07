package enumarations;

public enum CardType {
    STANDARD("STANDARD"),
    SILVER("SILVER"),
    GOLD("GOLD"),
    PREMIUM("PREMIUM");

    private final String value;

    CardType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
