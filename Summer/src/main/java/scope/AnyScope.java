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
        /*for (final Provider provider : providerMap)
            if (provider.get(c) != null)
                return provider;
        return null;*/
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

    /*@Override
    public void create(final Class c, final Supplier supplier) {
        final ProviderSingleton provider = new ProviderSingleton(c, obj);
        if (provider.create(obj))
            providerMap.add(provider);
    }*/
}
