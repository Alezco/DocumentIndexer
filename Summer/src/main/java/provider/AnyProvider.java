package provider;

import java.util.ArrayList;
import java.util.List;

public class AnyProvider<T> implements Provider {
    public final List<T> instanceList = new ArrayList<>();

    @Override
    public T get(final Class c) {
        for (T object : instanceList) {
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
