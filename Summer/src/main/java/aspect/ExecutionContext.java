package aspect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ExecutionContext {
    private final Method method;
    private final Object[] args;
    private final Object object;

    public ExecutionContext(final Method method, final Object[] args, final Object object) {
        this.method = method;
        this.args = args;
        this.object = object;
    }

    public Object proceed() {
        try {
            return method.invoke(object, args);
        } catch (final IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}