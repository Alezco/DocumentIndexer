package service;

import java.net.URL;

public interface ICrawler {

    void crawl(URL url);

    void extractLinks();

    void publish(URLRepo repo);

    void requestNextUrl();
}
