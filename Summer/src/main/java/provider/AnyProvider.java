package provider;

import java.util.ArrayList;
import java.util.List;

public abstract class AnyProvider<T> implements Provider {
    public final List<T> instanceList = new ArrayList<>();

    @Override
    public T get(final Class c) {
        for (final T object : instanceList) {
            if (object.getClass() == c)
                return object;
        }
        return null;
    }

    @Override
    public void create(final Class c, final Object obj) {
        instanceList.add((T)obj);
    }
}
