package aspect;

import java.lang.reflect.Method;

public class BeforeInvocation implements Aspect {

    private final Object object;
    public final Runnable runnable;

    public BeforeInvocation(final Object object, final Runnable runnable) {
        this.object = object;
        this.runnable = runnable;
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        runnable.run();
        return method.invoke(object, args);
    }
}
