package ru.moscowzoo.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import ru.moscowzoo.domain.animal.Rabbit;

class HerboTest {
    @Test
    void kindnessAboveThresholdAllowsContact() {
        Rabbit rabbit = new Rabbit("Тиша", 2, 1, 8);
        assertTrue(rabbit.isContactFriendly());
    }

    @Test
    void kindnessBelowThresholdDeniesContact() {
        Rabbit rabbit = new Rabbit("Тиша", 2, 1, 5);
        assertFalse(rabbit.isContactFriendly());
    }
}
