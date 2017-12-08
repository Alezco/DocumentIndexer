package provider;

public interface Provider<T> {
    Object get(final Class c);

    boolean create(final Object obj);
}
