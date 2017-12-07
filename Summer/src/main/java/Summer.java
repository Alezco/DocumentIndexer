import scope.AnyScope;

import java.util.Stack;

public class Summer {

    public final Stack<AnyScope> scopeStack;

    public Summer() {
        this.scopeStack = new Stack<>();
        this.scopeStack.push(new AnyScope());
    }

    public void bean(Class c, Object obj) {
        this.scopeStack.peek().create(c, obj);
    }

    public Object instanceOf(Class c) {
        for (AnyScope scope : scopeStack) {
            if (scope.get(c) != null)
                return scope.get(c).get(c);
        }
        return null;
    }

    public void removeScope() {
        this.scopeStack.pop();
    }
}
