package main;

import aspect.Aspect;
import aspect.BeforeInvocation;
import service.IURLRepo;
import service.URLRepo;

import java.lang.reflect.Proxy;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final List<URL> urlList = new ArrayList<>();

        final Summer summer = new Summer();
        summer.bean(URLRepo.class, new URLRepo());
        final URLRepo repo = (URLRepo) summer.instanceOf(URLRepo.class);
        BeforeInvocation beforeInvocation = new BeforeInvocation(repo);
        IURLRepo iurlRepo = (IURLRepo) Proxy.newProxyInstance(IURLRepo.class.getClassLoader(),
                new Class[] { IURLRepo.class },
                beforeInvocation);
        iurlRepo.testProxy();

        try {
            urlList.add(new URL("https://en.wikipedia.org/wiki/Rafic_Hariri"));
        } catch (final MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
