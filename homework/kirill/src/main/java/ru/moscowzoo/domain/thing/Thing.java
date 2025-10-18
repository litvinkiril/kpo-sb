package ru.moscowzoo.domain.thing;

import java.util.Objects;
import ru.moscowzoo.domain.IInventory;

/**
 * Base class for material inventory assets.
 */
public class Thing implements IInventory {
    private final String name;
    private final int inventoryNumber;

    public Thing(String name, int inventoryNumber) {
        this.name = Objects.requireNonNull(name, "name");
        this.inventoryNumber = inventoryNumber;
    }

    @Override
    public int getInventoryNumber() {
        return inventoryNumber;
    }

    @Override
    public String getInventoryName() {
        return name;
    }
}
