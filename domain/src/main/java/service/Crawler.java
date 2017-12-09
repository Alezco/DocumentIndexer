package service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Crawler implements ICrawler {

    private Document document;
    private final URLRepo urlRepo;

    public Crawler(final URLRepo repo) {
        this.urlRepo = repo;
    }

    @Override
    public void crawl(final URL url) {

        List<URL> extracted = new ArrayList<>();

        try {
            this.document = Jsoup.connect(url.toURI().toString()).get();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        final Elements links = this.document.select("a[href]");
        if (links != null) {
            for (final Element e : links) {
                try {
                    if (e.attr("href").startsWith("http")) {
                        final URL urlExtracted = new URL(links.attr("href"));
                        if (!extracted.contains(urlExtracted))
                            extracted.add(urlExtracted);
                    }

                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                }
            }
        }
        urlRepo.store(extracted);
    }

}
