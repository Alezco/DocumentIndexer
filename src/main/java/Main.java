import service.Crawler;
import service.Indexer;
import service.URLRepo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        URLRepo repo = new URLRepo();
        List<URL> urlList = new ArrayList<>();

        try {
            urlList.add(new URL("https://fr.wikipedia.org/wiki/Wikip%C3%A9dia:Accueil_principal"));
            repo.store(urlList);

            // Crawl URL
            Crawler crawler = new Crawler(repo);
            Indexer indexer = new Indexer(repo);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //Indexer indexer = new Indexer(repo);
        //Crawler crawler = new Crawler(repo);
    }
}
