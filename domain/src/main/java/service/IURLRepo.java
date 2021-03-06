package service;

import java.net.URL;
import java.util.List;

public interface IURLRepo {
    void receive(final List<URL> urls);

    void store(final List<URL> urls);

    void testProxy();

    void testProxy2();

    List<URL> searchTerm(final String query);
}
