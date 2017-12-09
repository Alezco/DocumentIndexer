package aspect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class MyProxy implements InvocationHandler {
    private final Object obj;
    private final List<Aspect> aspectList;

    public MyProxy(Object obj, List<Aspect> aspectList) {
        this.obj = obj;
        this.aspectList = aspectList;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        for (final Aspect aspect : aspectList)
            if (aspect.getClass().equals(BeforeInvocation.class))
                ((BeforeInvocation) aspect).runnable.run();
        final Object invocation = method.invoke(obj, args);
        for (final Aspect aspect : aspectList)
            if (aspect.getClass().equals(AfterInvocation.class))
                ((AfterInvocation) aspect).runnable.run();
        return invocation;
    }
}
