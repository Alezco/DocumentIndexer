package service;

import java.net.URL;
import java.util.List;

/**
 * Created by Guillaume on 06/12/2017.
 */
public interface IURLRepo {

    void receive(final List<URL> urls);

    void store(final List<URL> urls);

    URL request();

}
