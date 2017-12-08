package scope;

import provider.Provider;
import provider.ProviderSingleton;

import java.util.List;

public class AnyScope implements Scope  {

    private List<Provider> providerList;

    @Override
    public Provider get(final Class c) {
        for (final Provider provider : providerList)
        {
            if (provider.get(c) != null)
                return provider;
        }
        return null;
    }

    @Override
    public void create(final Class c, Object obj) {
        final ProviderSingleton provider = new ProviderSingleton();
        provider.create(c, obj);
        providerList.add(provider);
    }
}
