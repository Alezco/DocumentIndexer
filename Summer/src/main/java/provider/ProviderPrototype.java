package provider;

import java.util.function.Supplier;

/**
 * Created by Guillaume on 08/12/2017.
 */
public class ProviderPrototype extends AnyProvider {

    public void create(final Supplier supplier) {
        final Object object = supplier.get();
        this.instanceList.add(object);
    }
}
