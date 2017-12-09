package service;

import java.net.URL;
import java.util.List;

public interface IURLRepo {
    void receive(final List<URL> urls);

    void store(final List<URL> urls);

<<<<<<< 4bef57a0b5610fcb2fb1c37e5cae65845e7c9b89
    void testProxy();

    void testProxy2();

    List<URL> searchTerm(String query);
=======
    List<URL> searchTerm(final String query);
>>>>>>> Clean code
}
