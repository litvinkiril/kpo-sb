package ru.moscowzoo.di;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ServiceRegistryTest {
    @Test
    void resolvesSingletonsOnce() {
        ServiceRegistry registry = new ServiceRegistry();
        registry.registerSingleton(String.class, () -> new String("hello"));

        String first = registry.resolve(String.class);
        String second = registry.resolve(String.class);
        assertSame(first, second);
    }

    @Test
    void throwsWhenTypeMissing() {
        ServiceRegistry registry = new ServiceRegistry();
        assertThrows(IllegalStateException.class, () -> registry.resolve(Integer.class));
    }

    @Test
    void supplierMayDependOnOtherRegistrations() {
        ServiceRegistry registry = new ServiceRegistry();
        registry.registerSingleton(String.class, () -> "hi");
        registry.registerSingleton(Integer.class, () -> registry.resolve(String.class).length());

        assertEquals(2, registry.resolve(Integer.class));
    }
}
