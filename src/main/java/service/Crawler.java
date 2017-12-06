package service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Guillaume on 06/12/2017.
 */
public class Crawler implements ICrawler {

    private URL url;
    private URLRepo urlRepo;
    private Document document;

    public Crawler(URLRepo repo) {
        urlRepo = repo;
        this.url = repo.request();
        if (this.url != null)
            crawl(this.url);
    }

    // parcourir le contenu d'une page
    public void crawl(final URL url) {
        try {
            this.document = Jsoup.connect(url.toURI().toString()).get();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        extractLinks();
        requestNextUrl();
    }

    // Stockage des liens externes
    public void extractLinks() {

        ArrayList<URL> extracted = new ArrayList<URL>();
        for (Element e : this.document.select("a[href]")) {
            try {
                System.out.println(e.attr("abs:href"));
                extracted.add(new URL(e.attr("abs:href")));
            } catch (MalformedURLException exc) {
                exc.printStackTrace();
            }
        }
        urlRepo.store(extracted);
    }

    public void publish(final URLRepo repo) {
        // TODO
        /*List<URL> extracted = extractLinks();
        repo.store(extracted);*/
    }

    public void requestNextUrl() {
        this.url = urlRepo.request();
        if (url != null)
            crawl(url);
    }
}
