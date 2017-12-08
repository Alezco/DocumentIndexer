package provider;

public class ProviderSingleton extends AnyProvider {
    private static ProviderSingleton ourInstance = new ProviderSingleton();

    public static ProviderSingleton getInstance() {
        return ourInstance;
    }

    private ProviderSingleton() {
        super();
    }

    @Override
    public Object get(final Class c) {
        return super.get(c);
    }

    @Override
    public void create(final Class c, final Object obj) {
        super.create(c, obj);
    }
}
