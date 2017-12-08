package provider;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public abstract class AnyProvider implements Provider {
    public final List<Object> instanceList = new ArrayList<>();

    public Object get(final Class c) {
        for (final Object object : instanceList) {
            if (object.getClass() == c)
                return object;
        }
        return null;
    }

    public boolean create(final Object obj) {
        for (final Object object : instanceList) {
            if (object.getClass() == obj.getClass())
                return false;
        }
        instanceList.add(obj);
        return true;
    }

    public void create(final Supplier supplier) {
        final Object obj = supplier.get();
        instanceList.add(obj);
    }
}
