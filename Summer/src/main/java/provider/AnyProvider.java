package provider;

import aspect.Aspect;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public abstract class AnyProvider implements Provider {

    public final List<Object> instanceList = new ArrayList<>();
    public final List<Aspect> aspectList = new ArrayList<>();

    public Object get(final Class c) {
        for (final Object object : instanceList) {
            if (object.getClass().equals(c))
                return object;
        }
        return null;
    }

    public Provider addAspect(final Aspect aspect) {
        aspectList.add(aspect);
        return this;
    }

    /*public Object callProxy(final Class c, final Aspect aspect) {
        return Proxy.newProxyInstance(Provider.class.getClassLoader(), c.getInterfaces(), aspect);
    }*/
}
