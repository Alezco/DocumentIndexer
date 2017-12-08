package provider;

import java.util.ArrayList;
import java.util.List;

public abstract class AnyProvider implements Provider {
    public final List<Object> instanceList = new ArrayList<>();

    public Object get(final Class c) {
        for (final Object object : instanceList) {
            if (object.getClass() == c)
                return object;
        }
        return null;
    }
}
