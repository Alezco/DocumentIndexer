package provider;

public class ProviderSingleton implements Provider {
    private static ProviderSingleton ourInstance = new ProviderSingleton();

    public static ProviderSingleton getInstance() {
        return ourInstance;
    }

    private ProviderSingleton() {
    }

    @Override
    public Object get(final Class c) {
        return null;
    }

    @Override
    public void create(final Class c, final Object obj) {

    }
}
