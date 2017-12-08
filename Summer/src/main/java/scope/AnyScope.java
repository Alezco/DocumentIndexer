package scope;

import provider.Provider;
import provider.ProviderPrototype;
import provider.ProviderSingleton;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class AnyScope implements Scope  {

    public final List<Provider> providerList;

    public AnyScope() {
        this.providerList = new ArrayList<>();
    }

    @Override
    public Provider get(final Class c) {
        for (final Provider provider : providerList)
            if (provider.get(c) != null)
                return provider;
        return null;
    }

    public void create(final Class c, final Supplier supplier) {
        final ProviderPrototype provider = new ProviderPrototype();
        provider.create(supplier);
        providerList.add(provider);
    }

    @Override
    public void create(final Class c, Object obj) {
        final ProviderSingleton provider = new ProviderSingleton();
        provider.create(obj);
        providerList.add(provider);
    }
}
