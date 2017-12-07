package service;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class URLRepo implements IURLRepo {
    private final Stack<URL> notCrawledUrl;
    private final Set<URL> crawledUrl;

    public Stack<URL> getNotCrawledUrl() {
        return notCrawledUrl;
    }

    public Set<URL> getCrawledUrl() {
        return crawledUrl;
    }

    public URLRepo() {
        this.notCrawledUrl = new Stack<>();
        this.crawledUrl = new HashSet<>();
    }

    public void receive(final List<URL> urls) {
        notCrawledUrl.addAll(urls);
    }

    public void store(final List<URL> urls) {
        for (final URL url : urls) {
            try {
                if (!crawledUrl.contains(url.toURI().toString()))
                    notCrawledUrl.add(url);
            } catch (final URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    public URL request() {
        if (notCrawledUrl.isEmpty())
            return null;
        final URL url = notCrawledUrl.pop();
        crawledUrl.add(url);
        return url;
    }
}
