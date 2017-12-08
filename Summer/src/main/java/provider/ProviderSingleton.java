package provider;

public class ProviderSingleton extends AnyProvider {

    public boolean create(final Object obj) {
        for (final Object object : instanceList) {
            if (object.getClass() == obj.getClass())
                return false;
        }
        instanceList.add(obj);
        return true;
    }
}
