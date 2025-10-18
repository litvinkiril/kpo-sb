package ru.moscowzoo.domain.animal;

/**
 * Predator marker subclass.
 */
public abstract class Predator extends Animal {
    protected Predator(String nickname, int foodKgPerDay, int inventoryNumber) {
        super(nickname, foodKgPerDay, inventoryNumber);
    }
}
