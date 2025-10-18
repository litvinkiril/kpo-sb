package ru.moscowzoo.domain.animal;

/**
 * Herbivore with kindness score.
 */
public abstract class Herbo extends Animal {
    private final int kindness;

    protected Herbo(String nickname, int foodKgPerDay, int inventoryNumber, int kindness) {
        super(nickname, foodKgPerDay, inventoryNumber);
        if (kindness < 0 || kindness > 10) {
            throw new IllegalArgumentException("Kindness must be between 0 and 10");
        }
        this.kindness = kindness;
    }

    @Override
    public int getKindness() {
        return kindness;
    }
}
