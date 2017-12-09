package scope;

import provider.Provider;
import provider.ProviderPrototype;
import provider.ProviderSingleton;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class AnyScope implements Scope  {

    public final Map<Class<?>, Provider<?>> providerMap;

    public AnyScope() {
        this.providerMap = new HashMap<>();
    }

    @Override
    public <T> Provider<T> get(final Class<T> c) {
        return (Provider<T>) providerMap.get(c);
    }

    public <T> void createPrototype(final Class<T> c, final Supplier<T> supplier) {
        final ProviderPrototype provider = new ProviderPrototype(c, supplier);
        providerMap.put(c, provider);
    }

    public <T> void createSingleton(final Class<T> c, final Supplier<T> supplier) {
        final ProviderSingleton provider = new ProviderSingleton(c, supplier);
        providerMap.put(c, provider);
    }
}
