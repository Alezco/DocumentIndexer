package scope;

import aspect.Aspect;
import provider.Provider;

import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Supplier;

public interface Scope {
    <T> Provider<T> get(final Class<T> c);

    <T> void createPrototype(final Class<T> c, final Supplier<T> supplier, List<Aspect> aspectList, final Method method);

    <T> void createSingleton(final Class<T> c, final Supplier<T> supplier, List<Aspect> aspectList, final Method method);
}
