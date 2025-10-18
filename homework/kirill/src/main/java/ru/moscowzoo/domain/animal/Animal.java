package ru.moscowzoo.domain.animal;

import java.util.Objects;
import ru.moscowzoo.domain.IAlive;
import ru.moscowzoo.domain.IInventory;

/**
 * Base animal abstraction shared by both herbivores and predators.
 */
public abstract class Animal implements IAlive, IInventory {
    private final String nickname;
    private final int foodKgPerDay;
    private final int inventoryNumber;

    protected Animal(String nickname, int foodKgPerDay, int inventoryNumber) {
        if (foodKgPerDay <= 0) {
            throw new IllegalArgumentException("Food consumption must be positive");
        }
        this.nickname = Objects.requireNonNull(nickname, "nickname");
        this.foodKgPerDay = foodKgPerDay;
        this.inventoryNumber = inventoryNumber;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public int getFoodKgPerDay() {
        return foodKgPerDay;
    }

    @Override
    public int getInventoryNumber() {
        return inventoryNumber;
    }

    @Override
    public String getInventoryName() {
        return getClass().getSimpleName() + " " + nickname;
    }
}
