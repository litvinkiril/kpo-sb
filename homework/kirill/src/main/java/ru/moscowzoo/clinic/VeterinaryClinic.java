package ru.moscowzoo.clinic;

import java.util.Objects;
import ru.moscowzoo.domain.animal.Animal;

/**
 * Veterinary clinic responsible for approving new animals.
 */
public final class VeterinaryClinic {
    private final HealthInspection inspection;

    public VeterinaryClinic(HealthInspection inspection) {
        this.inspection = Objects.requireNonNull(inspection, "inspection");
    }

    public InspectionReport check(Animal candidate) {
        return inspection.inspect(candidate);
    }
}
