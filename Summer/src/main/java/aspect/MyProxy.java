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
        for (Aspect aspect : aspectList)
            if (aspect.getClass().equals(BeforeInvocation.class))
                ((BeforeInvocation) aspect).runnable.run();
        Object invocation = method.invoke(obj, args);
        for (Aspect aspect : aspectList)
            if (aspect.getClass().equals(AfterInvocation.class))
                ((AfterInvocation) aspect).runnable.run();
        return invocation;
    }
}
