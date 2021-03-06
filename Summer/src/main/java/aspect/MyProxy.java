package aspect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class MyProxy implements InvocationHandler {
    private final Object obj;
    private final List<Aspect> aspectList;
    private  final Method method;

    public MyProxy(final Object obj, final List<Aspect> aspectList, final Method method) {
        this.obj = obj;
        this.aspectList = aspectList;
        this.method = method;
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        for (final Aspect aspect : aspectList)
            if (aspect.getClass().equals(BeforeInvocation.class) && method.getName().equals(this.method.getName()))
                ((BeforeInvocation) aspect).runnable.run();
        final Object invocation = method.invoke(obj, args);
        for (final Aspect aspect : aspectList)
            if (aspect.getClass().equals(AfterInvocation.class) && method.getName().equals(this.method.getName()))
                ((AfterInvocation) aspect).runnable.run();
        return invocation;
    }
}
