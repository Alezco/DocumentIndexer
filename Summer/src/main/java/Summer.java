import scope.MyScope;

import java.util.Stack;

public class Summer {

    public final Stack<MyScope> scopeStack;

    public Summer() {
        this.scopeStack = new Stack<>();
        scopeStack.push(new MyScope());
    }

    public void bean(Class c, Object obj) {
        scopeStack.peek().create(c, obj);
    }

    public Object instanceOf(Class c) {
        for (MyScope scope : scopeStack) {
            if (scope.get(c) != null)
                return scope.get(c).get(c);
        }
        return null;
    }
}
