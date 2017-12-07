package service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Crawler implements ICrawler {

    private URL crawlingUrl;
    private Document document;
    private final URLRepo urlRepo;

    public Crawler(final URLRepo repo) {
        this.urlRepo = repo;

        // Request an url from the repository
        this.crawlingUrl = repo.request();
        if (this.crawlingUrl != null)
            crawl(this.crawlingUrl);
    }

    // Crawl a page content
    public void crawl(final URL url) {
        try {
            // Fetch and parse url
            this.document = Jsoup.connect(url.toURI().toString()).get();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        // Extract links and send it to repo
        publish(urlRepo);

        // Go back to beginning => next url
        //requestNextUrl();
    }

    public List<URL> extractLinks() {
        final ArrayList<URL> extracted = new ArrayList<URL>();
        for (Element e : this.document.select("a[href]")) {
            try {
                System.out.println(e.attr("abs:href"));
                if (e.attr("abs:href").startsWith("http"))
                    extracted.add(new URL(e.attr("abs:href")));
            } catch (MalformedURLException exc) {
                System.out.println(e.attr("abs:href"));
                exc.printStackTrace();
            }
        }
        // Extracting list of all links
        return extracted;
    }

    public void publish(final URLRepo repo) {
        final List<URL> extracted = extractLinks();
        // Send the result to the repository
        repo.store(extracted);
    }

    public void requestNextUrl() {
        this.crawlingUrl = urlRepo.request();
        if (crawlingUrl != null)
            crawl(crawlingUrl);
    }
}
