package provider;

import aspect.Aspect;

import java.util.ArrayList;
import java.util.List;

public abstract class AnyProvider<T> implements Provider<T> {

    public final List<Aspect> aspectList = new ArrayList<>();

    public abstract Object get(final Class c);

    public Provider addAspects(final List<Aspect> aspects) {
        if (aspects != null)
            aspectList.addAll(aspects);
        return this;
    }
}
