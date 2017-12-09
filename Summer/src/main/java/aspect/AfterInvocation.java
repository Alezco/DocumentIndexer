package aspect;

import java.lang.reflect.Method;

public class AfterInvocation implements Aspect {
    public final Runnable runnable;

    public AfterInvocation(final Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public Object invoke(final Object object, final Method method, final Object[] args) throws Throwable {
        Object invocation = method.invoke(object, args);
        runnable.run();
        return invocation;
    }
}
