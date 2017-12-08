package main;

import service.URLRepo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final List<URL> urlList = new ArrayList<>();

        final Summer summer = new Summer();
        test(summer);

        try {
            urlList.add(new URL("https://en.wikipedia.org/wiki/Rafic_Hariri"));
            //repo.store(urlList);
            //new Crawler(repo);
            //new Indexer(repo);

        } catch (final MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private static void test(Summer summer) {
        summer.bean(URLRepo.class, new URLRepo());
        final URLRepo repo = (URLRepo) summer.instanceOf(URLRepo.class);

        summer.addScope();
        summer.bean(URLRepo.class, new URLRepo());
        final URLRepo repo1 = (URLRepo) summer.instanceOf(URLRepo.class);

        if (repo == repo1)
            System.out.println("SUCCESS 1");
        else
            System.out.println("FAIL 1");

        summer.removeScope();
        summer.removeScope();
        summer.addScope();
        summer.bean(URLRepo.class, () -> new URLRepo());
        final URLRepo repo3 = (URLRepo) summer.instanceOf(URLRepo.class);
        summer.addScope();
        summer.bean(URLRepo.class, () -> new URLRepo());
        final URLRepo repo4 = (URLRepo) summer.instanceOf(URLRepo.class);

        if (repo3 == repo4)
            System.out.println("FAIL 2");
        else
            System.out.println("SUCCESS 2");
    }
}
