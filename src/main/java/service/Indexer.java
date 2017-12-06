package service;

import domain.RetroIndex;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Indexer implements IIndexer {
    public String request(final URLRepo repo) {
        URL url = repo.request();
        try {
            Document doc = Jsoup.connect(url.toURI().toString()).get();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public domain.Document index() {
        return null;
    }

    public void publish(final RetroIndex retroIndex) {

    }
}
