package aspect;

import java.lang.reflect.Method;

public class BeforeInvocation implements Aspect {
    public final Runnable runnable;

    public BeforeInvocation(final Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public Object invoke(final Object object, final Method method, final Object[] args) throws Throwable {
        runnable.run();
        return method.invoke(object, args);
    }
}
