package service;

import java.net.URL;
import java.util.List;

public interface ICrawler {

    void crawl(final URL url);

    List<URL> extractLinks();

    void publish(final URLRepo repo);

    void requestNextUrl();
}
