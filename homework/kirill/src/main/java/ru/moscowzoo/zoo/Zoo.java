package ru.moscowzoo.zoo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import ru.moscowzoo.clinic.InspectionReport;
import ru.moscowzoo.clinic.VeterinaryClinic;
import ru.moscowzoo.domain.IInventory;
import ru.moscowzoo.domain.animal.Animal;
import ru.moscowzoo.domain.animal.Herbo;
import ru.moscowzoo.domain.thing.Thing;

/**
 * Zoo aggregate root responsible for inventory and animal management.
 */
public final class Zoo {
    private final VeterinaryClinic clinic;
    private final List<Animal> animals = new ArrayList<>();
    private final List<IInventory> inventory = new ArrayList<>();

    public Zoo(VeterinaryClinic clinic) {
        this.clinic = Objects.requireNonNull(clinic, "clinic");
    }

    public InspectionReport admitAnimal(Animal candidate) {
        InspectionReport report = clinic.check(candidate);
        if (report.isAccepted()) {
            animals.add(candidate);
            inventory.add(candidate);
        }
        return report;
    }

    public void registerThing(Thing thing) {
        inventory.add(Objects.requireNonNull(thing, "thing"));
    }

    public List<Animal> getAnimals() {
        return Collections.unmodifiableList(animals);
    }

    public List<IInventory> getInventory() {
        return Collections.unmodifiableList(inventory);
    }

    public int totalFoodPerDay() {
        return animals.stream().mapToInt(Animal::getFoodKgPerDay).sum();
    }

    public List<Herbo> contactZooCandidates() {
        return animals.stream()
                .filter(a -> a instanceof Herbo)
                .map(a -> (Herbo) a)
                .filter(Herbo::isContactFriendly)
                .collect(Collectors.toUnmodifiableList());
    }
}
