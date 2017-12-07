package provider;

import java.lang.reflect.InvocationTargetException;
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
        try {
            final T instance = (T)c.getDeclaredConstructor().newInstance(c);
            instanceList.add(instance);
            return instance;
        } catch (final InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }
}
