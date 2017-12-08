package aspect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public interface Aspect extends InvocationHandler {
    Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable;
}
