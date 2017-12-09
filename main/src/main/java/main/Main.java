package main;

import aspect.AfterInvocation;
import aspect.Aspect;
import aspect.BeforeInvocation;
import service.IURLRepo;
import service.URLRepo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final List<URL> urlList = new ArrayList<>();

        final Summer summer = new Summer();

        ArrayList<Aspect> aspects = new ArrayList<>();
        aspects.add(new BeforeInvocation(() -> System.out.println("Before 1 !")));
        aspects.add(new BeforeInvocation(() -> System.out.println("Before 2 !")));
        aspects.add(new AfterInvocation(() -> System.out.println("After 1 !")));

        try {
            summer.bean(IURLRepo.class, new URLRepo(), aspects, URLRepo.class.getMethod("testProxy"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        final IURLRepo repo = (IURLRepo) summer.instanceOf(URLRepo.class);
        repo.testProxy();


        try {
            urlList.add(new URL("https://en.wikipedia.org/wiki/Rafic_Hariri"));
        } catch (final MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
