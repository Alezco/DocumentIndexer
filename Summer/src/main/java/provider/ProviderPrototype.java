package provider;

import aspect.MyProxy;

import java.lang.reflect.Proxy;
import java.util.function.Supplier;

public class ProviderPrototype<T> extends AnyProvider<T> {
    private final Class<T> classType;
    private final Supplier<T> supplier;

    public ProviderPrototype(Class<T> classType, Supplier<T> supplier) {
        this.classType = classType;
        this.supplier = supplier;
    }

    @Override
    public Object get(final Class c) {
        Object obj = supplier.get();
        MyProxy myProxy = new MyProxy(obj, aspectList);
        return Proxy.newProxyInstance(classType.getClassLoader(), new Class[] {classType}, myProxy);
    }
}
