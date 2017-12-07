import service.Crawler;
import service.Indexer;
import service.URLRepo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final URLRepo repo = new URLRepo();
        final List<URL> urlList = new ArrayList<>();

        try {
            urlList.add(new URL("https://pastebin.com/raw/p1rixE68"));
            urlList.add(new URL("https://pastebin.com/raw/HV7RHk5d"));
            urlList.add(new URL("https://pastebin.com/raw/SS0QnUd3"));
            repo.store(urlList);

            // Crawl URL
            new Crawler(repo);
            new Indexer(repo);

        } catch (final MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
