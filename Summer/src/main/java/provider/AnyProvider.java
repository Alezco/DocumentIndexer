package provider;

import aspect.Aspect;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public abstract class AnyProvider implements Provider {

    public final List<Object> instanceList = new ArrayList<>();

    // CallProxy Proxy

    public void callProxy(final Class c, final Aspect aspect) {

        Proxy.newProxyInstance(Provider.class.getClassLoader(),
                Provider.class.getInterfaces(),
                aspect);
    }

    public Object get(final Class c) {
        for (final Object object : instanceList) {
            if (object.getClass().equals(c))
                return object;
        }
        return null;
    }
}
