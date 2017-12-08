package scope;

import provider.Provider;
import provider.ProviderSingleton;

import java.util.ArrayList;
import java.util.List;

public class AnyScope implements Scope  {

    public final List<Provider> providerList;

    public AnyScope() {
        this.providerList = new ArrayList<>();
    }


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
