package provider;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public abstract class AnyProvider<T> implements Provider {
    public final List<T> instanceList = new ArrayList<>();

    public T get(final Class c) {
        for (final T object : instanceList) {
            if (object.getClass() == c)
                return object;
        }
        return null;
    }

    public boolean create(final Object obj) {
        for (final T object : instanceList) {
            if (object.getClass() == obj.getClass())
                return false;
        }
        instanceList.add((T)obj);
        return true;
    }

    public void create(final Supplier supplier) {
        final T obj = (T) supplier.get();
        instanceList.add(obj);
    }
}
