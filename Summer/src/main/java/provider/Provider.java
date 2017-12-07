package provider;

public interface Provider {
    Object get(final Class c);

    void create(final Class c, Object obj);
}
