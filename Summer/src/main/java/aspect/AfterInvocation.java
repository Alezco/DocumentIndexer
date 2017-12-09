package aspect;

import java.lang.reflect.Method;

public class AfterInvocation implements Aspect {

    private final Object object;
    public final Runnable runnable;

    public AfterInvocation(final Object object, final Runnable runnable) {
        this.object = object;
        this.runnable = runnable;
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        Object invocation = method.invoke(object, args);
        runnable.run();
        return invocation;
    }
}
