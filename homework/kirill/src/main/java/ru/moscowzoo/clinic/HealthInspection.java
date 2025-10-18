package ru.moscowzoo.clinic;

import ru.moscowzoo.domain.animal.Animal;

/**
 * Describes how the veterinary clinic inspects new animals.
 */
@FunctionalInterface
public interface HealthInspection {
    InspectionReport inspect(Animal candidate);
}
