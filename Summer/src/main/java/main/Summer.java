package main;

import scope.AnyScope;

import java.util.Stack;

public class Summer {

    public final Stack<AnyScope> scopeStack;

    public Summer() {
        this.scopeStack = new Stack<>();
        this.addScope();
    }

    public void bean(final Class c, final Object obj) {
        this.scopeStack.peek().create(c, obj);
    }

    public Object instanceOf(final Class c) {
        final Stack<?> temp = (Stack<?>) scopeStack.clone();
        while (temp.size() > 0) {
            final AnyScope scope = (AnyScope) temp.pop();
            if (scope.get(c) != null)
                return scope.get(c).get(c);
        }
        return null;
    }

    public void removeScope() {
        this.scopeStack.pop();
    }

    public void addScope() {
        this.scopeStack.push(new AnyScope());
    }
}
