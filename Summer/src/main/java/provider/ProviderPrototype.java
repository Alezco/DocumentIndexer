package provider;

import java.util.function.Supplier;

public class ProviderPrototype extends AnyProvider {
    public void create(final Supplier supplier) {
        final Object object = supplier.get();
        this.instanceList.add(object);
    }
}
