package main;

import scope.AnyScope;

import java.util.Stack;
import java.util.function.Supplier;

public class Summer {

    public final Stack<AnyScope> scopeStack;

    public Summer() {
        this.scopeStack = new Stack<>();
        this.addScope();
    }

    public <T> void bean(final Class<T> c, final T obj) {
        for (final AnyScope scope : scopeStack)
            if (scope.get(c) != null)
                return;
        this.scopeStack.peek().createSingleton(c, () -> obj);
    }

    public <T> void bean(final Class<T> c, final Supplier<T> supplier) {
        this.scopeStack.peek().createPrototype(c, supplier);
    }

    public <T> Object instanceOf(final Class<T> c) {
        final Stack<?> temp = (Stack<?>) scopeStack.clone();
        while (!temp.isEmpty()) {
            final AnyScope scope = (AnyScope) temp.pop();
            if (scope.get(c.getInterfaces()[0]) != null)
                return scope.get(c.getInterfaces()[0]).get(c);
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
