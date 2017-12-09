package aspect;

import java.lang.reflect.Method;

public interface Aspect {
    Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable;
}
