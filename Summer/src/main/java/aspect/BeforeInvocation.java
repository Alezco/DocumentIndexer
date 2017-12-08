package aspect;

import java.lang.reflect.Method;

public class BeforeInvocation implements Aspect {

    private final Object object;

    public BeforeInvocation(final Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        System.out.println("==BEFORE==");
        return method.invoke(object, args);
    }
}
