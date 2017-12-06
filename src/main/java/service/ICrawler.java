package service;

import java.util.List;

/**
 * Created by Guillaume on 06/12/2017.
 */
public interface ICrawler {

    void crawl(String url);

    List<String> extractLinks();

    void publish(Repo repo);

    void requestNextUrl();
}
