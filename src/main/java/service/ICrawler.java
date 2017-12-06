package service;

import java.net.URL;
import java.util.List;

/**
 * Created by Guillaume on 06/12/2017.
 */
public interface ICrawler {

    void crawl(URL url);

    List<URL> extractLinks();

    void publish(URLRepo repo);

    void requestNextUrl();
}
