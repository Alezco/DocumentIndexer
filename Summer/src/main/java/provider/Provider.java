package provider;

public interface Provider {
    Object get(final Class c);

    boolean create(final Object obj);
}
