package service;

import java.net.URL;
import java.util.List;

/**
 * Created by Guillaume on 06/12/2017.
 */
public class Crawler implements ICrawler {

    // parcourir le contenu d'une page
    public void crawl(final URL url) {

    }

    // Stockage des liens externes
    public List<URL> extractLinks() {
        return null;
    }

    public void publish(final Repo repo) {

    }

    public void requestNextUrl() {

    }
}
