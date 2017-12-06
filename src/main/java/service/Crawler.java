package service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guillaume on 06/12/2017.
 */
public class Crawler implements ICrawler {

    private URL url;

    public Crawler() {
    }

    // parcourir le contenu d'une page
    public void crawl(final URL url) {
        this.url = url;
    }

    // Stockage des liens externes
    public List<URL> extractLinks() {

        List<URL> urls = new ArrayList<URL>();
        try {
            Document doc = Jsoup.connect(url.toURI().toString()).get();
            Elements links = doc.getElementsByTag("a");

            for (Element e : links)
                urls.add(new URL(e.attr("abs:href").toString()));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return urls;
    }

    public void publish(final URLRepo repo) {
        List<URL> extracted = extractLinks();
        repo.store(extracted);
    }

    public void requestNextUrl() {

    }
}
