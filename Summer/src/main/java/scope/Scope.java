package scope;

import provider.Provider;

import java.util.function.Supplier;

public interface Scope {
    <T> Provider<T> get(final Class<T> c);

    <T> void createPrototype(final Class<T> c, final Supplier<T> supplier);

    <T> void createSingleton(final Class<T> c, final Supplier<T> supplier);
}
