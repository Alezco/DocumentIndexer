package service;

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
        // TODO
    }

    public void store(final List<URL> urls) {
        for (URL url : urls) {
            if (!notCrawledUrl.contains(url))
                notCrawledUrl.add(url);
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
