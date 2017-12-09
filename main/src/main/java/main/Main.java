package main;

import aspect.AfterInvocation;
import aspect.BeforeInvocation;
import service.IURLRepo;
import service.URLRepo;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final List<URL> urlList = new ArrayList<>();

        final Summer summer = new Summer();
        summer.bean(URLRepo.class, new URLRepo());
        final URLRepo repo = (URLRepo) summer.instanceOf(URLRepo.class);
        final BeforeInvocation beforeInvocation = new BeforeInvocation(repo, () -> System.out.println("===Before invocation===="));
        final AfterInvocation afterInvocation = new AfterInvocation(repo, () -> System.out.println("====After invocation===="));
        final IURLRepo iurlRepo = (IURLRepo) summer.callProxy(URLRepo.class, beforeInvocation);
        // callProxy(.class, lambda) ===> .class => instance (instanceOf ?)
        final IURLRepo iurlRepo2 = (IURLRepo) summer.callProxy(URLRepo.class, afterInvocation);
        iurlRepo.testProxy();
        System.out.println("========================");
        iurlRepo2.testProxy();

        try {
            urlList.add(new URL("https://en.wikipedia.org/wiki/Rafic_Hariri"));
        } catch (final MalformedURLException e) {
            e.printStackTrace();
        }

        /*URLRepo repo = new URLRepo();

        ArrayList<URL> urls = new ArrayList<>();
        try {
            urls.add(new URL("https://pastebin.com/raw/07aPR2K4"));
            urls.add(new URL("https://pastebin.com/raw/jzAtxp85"));
            urls.add(new URL("https://pastebin.com/raw/U7PDZqC7"));
            repo.store(urls);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        repo.searchTerm("qui");*/

    }
}
