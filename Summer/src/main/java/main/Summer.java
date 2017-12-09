package main;

import aspect.Aspect;
import provider.Provider;
import scope.AnyScope;

import java.lang.reflect.Proxy;
import java.util.Stack;
import java.util.function.Supplier;

public class Summer {

    public final Stack<AnyScope> scopeStack;

    public Summer() {
        this.scopeStack = new Stack<>();
        this.addScope();
    }

    public void bean(final Class c, final Object obj) {
        for (final AnyScope scope : scopeStack)
            if (scope.get(c) != null)
                return;
        this.scopeStack.peek().create(c, obj);
    }

    public void bean(final Class c, final Supplier supplier) {
        this.scopeStack.peek().create(c, supplier);
    }

    public Object instanceOf(final Class c) {
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
