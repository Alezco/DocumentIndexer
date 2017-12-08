package scope;

public interface Scope {
    Object get(final Class c);

    void create(final Class c, final Object obj);
}