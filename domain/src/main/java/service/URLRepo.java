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

    public List<URL> searchTerm(String query) {
        Crawler crawler = new Crawler(this);

        while (!this.notCrawledUrl.isEmpty()) {
            final URL url = notCrawledUrl.pop();
            crawledUrl.add(url);
            crawler.crawl(url);
        }

        Indexer indexer = new Indexer(this);
        List<Document> documents = new ArrayList<>();

        for (URL url : crawledUrl) {
            indexer.request(url);
        }

        RetroIndex retroIndex = indexer.retroIndex;
        documents = retroIndex.documents;
        System.out.println("size = " + documents.size());

        HashMap<URL, Double> result = new HashMap<>();

        for (Document doc : retroIndex.documents) {
            for (Term term : doc.terms) {
                if (term.token.equals(query)) {
                    float tf = term.frequency;
                    double x = (double)retroIndex.documents.size();
                    double y = (double)retroIndex.map.get(query).size();
                    double ratio = x / y;
                    double idf = Math.log10(ratio + 1);
                    result.put(doc.url, tf * idf);
                }
            }
        }

        List<URL> matchingUrls = new ArrayList<>();
        System.out.println("==========" + " Search Term : " + query + " ==========");
        for (HashMap.Entry<URL, Double> entry : result.entrySet()) {
            System.out.println("URL : " + entry.getKey() + " idf : " + entry.getValue());
            matchingUrls.add(entry.getKey());
        }

        return matchingUrls;
    }

    public void testProxy() {
        System.out.println("Test Proxy from URL Repo");
    }
}
