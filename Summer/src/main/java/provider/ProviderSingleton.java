package provider;

import aspect.MyProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.function.Supplier;

public class ProviderSingleton<T> extends AnyProvider<T> {
    private final Class<T> classType;
    private final T instance;

    public ProviderSingleton(final Class<T> classType, final Supplier<T> supplier, final Method method) {
        this.classType = classType;
        T tmp = supplier.get();
        MyProxy myProxy = new MyProxy(tmp, aspectList, method);
        this.instance = (T) Proxy.newProxyInstance(classType.getClassLoader(), new Class[] {classType}, myProxy);
    }

    @Override
    public Object get(final Class c) {
        return this.instance;
    }
}
