package service;

import java.net.URL;
import java.util.List;

public interface ICrawler {

    List<URL> crawl(URL url);

    List<URL> extractLinks();

    void publish(URLRepo repo);

    void requestNextUrl();
}
