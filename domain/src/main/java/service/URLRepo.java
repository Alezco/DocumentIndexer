package service;

import valueobject.Document;
import valueobject.RetroIndex;
import valueobject.Term;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class URLRepo implements IURLRepo {
    private final Stack<URL> notCrawledUrl;
    private final Set<URL> crawledUrl;

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

    public List<URL> searchTerm(String query) {
        final Crawler crawler = new Crawler(this);
        while (!this.notCrawledUrl.isEmpty()) {
            final URL url = notCrawledUrl.pop();
            crawledUrl.add(url);
            crawler.crawl(url);
        }

        final Indexer indexer = new Indexer(this);
        List<Document> documents = new ArrayList<>();

        for (final URL url : crawledUrl) {
            indexer.request(url);
        }

        final RetroIndex retroIndex = indexer.retroIndex;
        final HashMap<URL, Double> result = new HashMap<>();

        for (final Document doc : retroIndex.documents) {
            for (final Term term : doc.terms) {
                if (term.token.equals(query)) {
                    final float tf = term.frequency;
                    final double x = (double)retroIndex.documents.size();
                    final double y = (double)retroIndex.map.get(query).size();
                    final double ratio = x / y;
                    final double idf = Math.log10(ratio + 1);
                    result.put(doc.url, tf * idf);
                }
            }
        }

        final List<URL> matchingUrls = new ArrayList<>();
        System.out.println("==========" + " Search Term : " + query + " ==========");
        for (final HashMap.Entry<URL, Double> entry : result.entrySet()) {
            System.out.println("URL : " + entry.getKey() + " idf : " + entry.getValue());
            matchingUrls.add(entry.getKey());
        }
        return matchingUrls;
    }

    public void testProxy() {
        System.out.println("Test Proxy from URL Repo");
    }

    public void testProxy2() {
        System.out.println("Test Proxy 2 from URL Repo");
    }

}
