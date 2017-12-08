package provider;

public interface Provider<T> {
    Object get(final Class c);

    void create(final Object obj);
}
