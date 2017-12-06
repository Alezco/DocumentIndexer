
import service.Crawler;
import service.Indexer;
import service.URLRepo;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Guillaume on 06/12/2017.
 */
public class Main {
    public static void main(String[] args) {

        URLRepo repo = new URLRepo();

        Indexer indexer = new Indexer();



        Crawler crawler = new Crawler();
        try {
            crawler.crawl(new URL("https://fr.wikipedia.org/wiki/Wikip%C3%A9dia:Accueil_principal"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
