package aspect;

import java.lang.reflect.Method;

public class AroundInvocation implements Aspect {
    public final Runnable runnable;

    public AroundInvocation(final Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public Object invoke(final Object object, final Method method, final Object[] args) throws Throwable {
        return null;
    }
}
