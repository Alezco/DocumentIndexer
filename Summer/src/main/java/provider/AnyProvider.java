package provider;

import aspect.Aspect;
import aspect.MyProxy;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public abstract class AnyProvider<T> implements Provider<T> {

    public final List<Object> instanceList = new ArrayList<>();
    public final List<Aspect> aspectList = new ArrayList<>();

    public abstract Object get(final Class c); /*{
        /*Object obj = null;
        for (final Object object : instanceList) {
            if (object.getClass().equals(c))
                obj = object;
        }
        MyProxy myProxy = new MyProxy(obj, aspectList);
        Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[] {}, myProxy);
        return null;
    }*/

    public Provider addAspect(final Aspect aspect) {
        aspectList.add(aspect);
        return this;
    }

    /*public Object callProxy(final Class c, final Aspect aspect) {
        return Proxy.newProxyInstance(Provider.class.getClassLoader(), c.getInterfaces(), aspect);
    }*/
}
