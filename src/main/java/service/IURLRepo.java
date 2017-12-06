package service;

import java.util.List;

/**
 * Created by Guillaume on 06/12/2017.
 */
public interface IURLRepo {

    void receive(final List<String> urls);

    void store(final List<String> urls);

    String request();

}
