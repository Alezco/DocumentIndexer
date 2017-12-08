package aspect;

import java.lang.reflect.Method;

public class AfterInvocation implements Aspect {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
