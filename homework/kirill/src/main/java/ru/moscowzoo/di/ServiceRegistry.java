package ru.moscowzoo.di;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Minimalistic dependency registry with lazy singletons.
 */
public final class ServiceRegistry {
    private final Map<Class<?>, Supplier<?>> registrations = new HashMap<>();
    private final Map<Class<?>, Object> singletons = new HashMap<>();

    public <T> void registerSingleton(Class<T> type, Supplier<? extends T> supplier) {
        registrations.put(type, () -> Objects.requireNonNull(supplier.get()));
    }

    @SuppressWarnings("unchecked")
    public <T> T resolve(Class<T> type) {
        if (singletons.containsKey(type)) {
            return (T) singletons.get(type);
        }
        Supplier<?> supplier = registrations.get(type);
        if (supplier == null) {
            throw new IllegalStateException("No registration for " + type.getName());
        }
        T instance = (T) supplier.get();
        singletons.put(type, instance);
        return instance;
    }
}
