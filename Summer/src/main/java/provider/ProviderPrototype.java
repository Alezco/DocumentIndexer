package provider;

import aspect.MyProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.function.Supplier;

public class ProviderPrototype<T> extends AnyProvider<T> {
    private final Class<T> classType;
    private final Supplier<T> supplier;
    private final Method method;

    public ProviderPrototype(Class<T> classType, Supplier<T> supplier, Method method) {
        this.classType = classType;
        this.supplier = supplier;
        this.method = method;
    }

    @Override
    public Object get(final Class c) {
        Object obj = supplier.get();
        MyProxy myProxy = new MyProxy(obj, aspectList, this.method);
        return Proxy.newProxyInstance(classType.getClassLoader(), new Class[] {classType}, myProxy);
    }
}
