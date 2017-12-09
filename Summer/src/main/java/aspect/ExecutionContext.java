package aspect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ExecutionContext {
    private final Object proxy;
    private final Method method;
    private final Object[] args;
    private final Object object;

    public ExecutionContext(final Object proxy, final Method method, final Object[] args, final Object object) {
        this.proxy = proxy;
        this.method = method;
        this.args = args;
        this.object = object;
    }

    Object proceed() {
        try {
            return method.invoke(object, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}