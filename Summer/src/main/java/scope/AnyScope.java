package scope;

import provider.Provider;
import provider.ProviderSingleton;

import java.util.List;

/**
 * Created by Guillaume on 07/12/2017.
 */
public class AnyScope implements Scope  {

    private List<Provider> providerList;

    @Override
    public Provider get(final Class c) {
        for (Provider provider : providerList)
        {
            if (provider.get(c) != null)
                return provider;
        }
        return null;
    }

    @Override
    public void create(final Class c, Object obj) {
        ProviderSingleton provider = new ProviderSingleton();
        provider.create(c, obj);
        providerList.add(provider);
    }
}
