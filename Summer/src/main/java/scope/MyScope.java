package scope;

import provider.Provider;

import java.util.List;

/**
 * Created by Guillaume on 07/12/2017.
 */
public class MyScope implements Scope  {

    private List<Provider> providerList;

    @Override
    public Provider get(final Class c) {
        for (Provider provider : providerList)
        {

        }
        return null;
    }
}
