package main;

import aspect.Aspect;
import scope.AnyScope;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Stack;
import java.util.function.Supplier;

public class Summer {

    public final Stack<AnyScope> scopeStack;

    public Summer() {
        this.scopeStack = new Stack<>();
        this.addScope();
    }

    public <T> void bean(final Class<T> c, final T obj, final List<Aspect> aspectList, final Method method) {
        for (final AnyScope scope : scopeStack)
            if (scope.get(c) != null)
                return;
        this.scopeStack.peek().createSingleton(c, () -> obj, aspectList, method);
    }

    public <T> void bean(final Class<T> c, final Supplier<T> supplier, final List<Aspect> aspectList, final Method method) {
        this.scopeStack.peek().createPrototype(c, supplier, aspectList, method);
    }

    public <T> Object instanceOf(final Class<T> c) {
        final Stack<?> temp = (Stack<?>) scopeStack.clone();
        while (!temp.isEmpty()) {
            final AnyScope scope = (AnyScope) temp.pop();
            if (scope.get(c) != null)
                return scope.get(c).get(c);
        }
        return null;
    }

    public void removeScope() {
        if (!this.scopeStack.isEmpty())
            this.scopeStack.pop();
    }

    public void addScope() {
        this.scopeStack.push(new AnyScope());
    }
}
