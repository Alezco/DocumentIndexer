package main;

import service.URLRepo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //final URLRepo repo = new URLRepo();
        final List<URL> urlList = new ArrayList<>();

        Summer summer = new Summer();

        summer.bean(URLRepo.class, new URLRepo());
        URLRepo repo = (URLRepo) summer.instanceOf(URLRepo.class);

        summer.addScope();
        summer.bean(URLRepo.class, new URLRepo());
        URLRepo repo1 = (URLRepo) summer.instanceOf(URLRepo.class);

        if (repo == repo1)
            System.out.println("SUCCESS 1");
        else
            System.out.println("FAIL 1");

        try {
            urlList.add(new URL("https://en.wikipedia.org/wiki/Rafic_Hariri"));
            //repo.store(urlList);

            // Crawl URL
            //new Crawler(repo);
            //new Indexer(repo);

        } catch (final MalformedURLException e) {
            e.printStackTrace();
        }

        summer.removeScope();
        summer.removeScope();
        summer.addScope();
        summer.bean(URLRepo.class, () -> new URLRepo());
        URLRepo repo3 = (URLRepo) summer.instanceOf(URLRepo.class);
        summer.bean(URLRepo.class, () -> new URLRepo());
        URLRepo repo4 = (URLRepo) summer.instanceOf(URLRepo.class);

        if (repo3 == repo4)
            System.out.println("FAIL 2");
        else
            System.out.println("SUCCESS 2");
    }
}
