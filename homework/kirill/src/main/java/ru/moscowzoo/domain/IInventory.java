package ru.moscowzoo.domain;

/**
 * Interface for assets tracked during inventory.
 */
public interface IInventory {
    /**
     * @return inventory identifier for reports.
     */
    int getInventoryNumber();

    /**
     * @return descriptive label for the inventory record.
     */
    String getInventoryName();
}
