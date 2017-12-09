package provider;

import aspect.MyProxy;

import java.lang.reflect.Proxy;
import java.util.function.Supplier;

public class ProviderSingleton<T> extends AnyProvider<T> {
    /*public boolean create(final Object obj) {
        for (final Object object : instanceList) {
            if (object.getClass().equals(obj.getClass()))
                return false;
        }
        instanceList.add(obj);
        return true;
    }*/

    private final Class<T> classType;
    private final T instance;

    public ProviderSingleton(Class<T> classType, Supplier<T> supplier) {
        this.classType = classType;
        T tmp = supplier.get();
        MyProxy myProxy = new MyProxy(tmp, aspectList);
        Proxy.newProxyInstance(classType.getClassLoader(), new Class[] {classType}, myProxy);
        this.instance = (T) Proxy.newProxyInstance(classType.getClassLoader(), new Class[] {classType}, myProxy);

    }

    @Override
    public Object get(final Class c) {
        return this.instance;
    }
}
