package service;

import java.net.URL;
import java.util.List;
import java.util.Stack;

public class URLRepo implements IURLRepo {

    private final Stack<URL> notCrawledUrl;
    private final Stack<URL> crawledUrl;

    public Stack<URL> getNotCrawledUrl() {
        return notCrawledUrl;
    }

    public Stack<URL> getCrawledUrl() {
        return crawledUrl;
    }

    public URLRepo() {
        this.notCrawledUrl = new Stack<URL>();
        this.crawledUrl = new Stack<URL>();
    }

    public void receive(final List<URL> urls) {
        //
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
        URL url = notCrawledUrl.pop();
        crawledUrl.add(url);
        return url;
    }
}
