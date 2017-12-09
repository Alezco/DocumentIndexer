package scope;

import aspect.Aspect;
import provider.Provider;
import provider.ProviderPrototype;
import provider.ProviderSingleton;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class AnyScope implements Scope  {

    public final Map<Class<?>, Provider<?>> providerMap;

    public AnyScope() {
        this.providerMap = new HashMap<>();
    }

    @Override
    public <T> Provider<T> get(final Class<T> c) {
        return (Provider<T>) providerMap.get(c.getClass());
    }

    public <T> void createPrototype(final Class<T> c, final Supplier<T> supplier, final List<Aspect> aspectList, final Method method) {
        final ProviderPrototype provider = new ProviderPrototype(c, supplier, method);
        provider.addAspects(aspectList);
        providerMap.put(c.getClass(), provider);
    }

    public <T> void createSingleton(final Class<T> c, final Supplier<T> supplier, final List<Aspect> aspectList, final Method method) {
        final ProviderSingleton provider = new ProviderSingleton(c, supplier, method);
        provider.addAspects(aspectList);
        providerMap.put(c.getClass(), provider);
    }
}
