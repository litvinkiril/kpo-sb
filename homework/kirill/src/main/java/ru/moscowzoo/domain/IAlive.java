package ru.moscowzoo.domain;

/**
 * Marker for living objects tracked by the zoo.
 */
public interface IAlive {
    /**
     * @return food consumption in kilograms per day.
     */
    int getFoodKgPerDay();

    /**
     * Herbivores override to expose their kindness level.
     *
     * @return kindness score between 0 and 10.
     */
    default int getKindness() {
        return 0;
    }

    /**
     * @return {@code true} when visitors may interact safely.
     */
    default boolean isContactFriendly() {
        return getKindness() > 5;
    }
}
