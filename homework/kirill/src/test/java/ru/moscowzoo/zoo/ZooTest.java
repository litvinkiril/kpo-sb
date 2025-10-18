package ru.moscowzoo.zoo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;
import ru.moscowzoo.clinic.HealthInspection;
import ru.moscowzoo.clinic.InspectionReport;
import ru.moscowzoo.clinic.VeterinaryClinic;
import ru.moscowzoo.domain.IInventory;
import ru.moscowzoo.domain.animal.Monkey;
import ru.moscowzoo.domain.animal.Rabbit;
import ru.moscowzoo.domain.animal.Tiger;
import ru.moscowzoo.domain.thing.Table;

class ZooTest {
    @Test
    void admitsAnimalWhenClinicApproves() {
        Zoo zoo = new Zoo(new VeterinaryClinic(alwaysAccept()));
        InspectionReport report = zoo.admitAnimal(new Tiger("Снежок", 7, 10));

        assertTrue(report.isAccepted());
        assertEquals(1, zoo.getAnimals().size());
        assertEquals(1, zoo.getInventory().size());
    }

    @Test
    void rejectsAnimalWhenClinicDeclines() {
        Zoo zoo = new Zoo(new VeterinaryClinic(alwaysReject()));
        InspectionReport report = zoo.admitAnimal(new Tiger("Снежок", 7, 10));

        assertFalse(report.isAccepted());
        assertTrue(zoo.getAnimals().isEmpty());
    }

    @Test
    void calculatesFoodAndContactZoo() {
        Zoo zoo = new Zoo(new VeterinaryClinic(alwaysAccept()));
        zoo.admitAnimal(new Rabbit("Кроша", 3, 11, 9));
        zoo.admitAnimal(new Monkey("Флинт", 5, 12, 4));

        assertEquals(8, zoo.totalFoodPerDay());
        assertEquals(1, zoo.contactZooCandidates().size());
    }

    @Test
    void maintainsInventoryOfThingsAndAnimals() {
        Zoo zoo = new Zoo(new VeterinaryClinic(alwaysAccept()));
        zoo.registerThing(new Table("Рабочий стол", 5));
        zoo.admitAnimal(new Rabbit("Кроша", 3, 11, 9));

        List<IInventory> inventory = zoo.getInventory();
        assertEquals(2, inventory.size());
        assertTrue(inventory.stream().anyMatch(item -> item.getInventoryName().contains("Рабочий стол")));
    }

    private HealthInspection alwaysAccept() {
        return animal -> InspectionReport.accepted("ok");
    }

    private HealthInspection alwaysReject() {
        return animal -> InspectionReport.rejected("bad");
    }
}
