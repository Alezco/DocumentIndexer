package aspect;

import java.lang.reflect.Method;

public class AfterInvocation implements Aspect {

    private final Object object;

    public AfterInvocation(final Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invocation = method.invoke(object, args);
        System.out.println("After invocation");
        return invocation;
    }
}
