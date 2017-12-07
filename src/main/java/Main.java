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
            urlList.add(new URL("https://pastebin.com/raw/07aPR2K4"));
            urlList.add(new URL("https://pastebin.com/raw/jzAtxp85"));
            urlList.add(new URL("https://pastebin.com/raw/U7PDZqC7"));

            repo.store(urlList);

            Indexer indexer = new Indexer(repo);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //Indexer indexer = new Indexer(repo);
        //Crawler crawler = new Crawler(repo);
    }
}
