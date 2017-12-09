package main;

import aspect.AfterInvocation;
import aspect.Aspect;
import aspect.BeforeInvocation;
import org.junit.Before;
import org.junit.Test;
import service.IURLRepo;
import service.URLRepo;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.function.Supplier;

public class AspectTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setupStream() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void beforeInvocationSingletonTest() {

        final ArrayList<Aspect> aspects = new ArrayList<>();
        aspects.add(new BeforeInvocation(() -> System.out.println("Before Method Singleton")));

        final Summer summer = new Summer();
        try {
            summer.bean(IURLRepo.class, new URLRepo(), aspects, URLRepo.class.getMethod("testProxy"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        final IURLRepo repo = (IURLRepo) summer.instanceOf(URLRepo.class);
        repo.testProxy();

        assert outContent.toString().contains("Before Method Singleton");
        System.setOut(null);
    }

    @Test
    public void beforeInvocationPrototypeTest() {
        final ArrayList<Aspect> aspects = new ArrayList<>();
        aspects.add(new BeforeInvocation(() -> System.out.println("Before Method Prototype")));

        final Summer summer = new Summer();
        try {
            summer.bean(IURLRepo.class, (Supplier<IURLRepo>) () -> new URLRepo() , aspects, URLRepo.class.getMethod("testProxy"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        final IURLRepo repo = (IURLRepo) summer.instanceOf(URLRepo.class);
        repo.testProxy();

        assert outContent.toString().contains("Before Method Prototype");
        System.setOut(null);
    }

    @Test
    public void afterInvocationPrototypeTest() {
        final ArrayList<Aspect> aspects = new ArrayList<>();
        aspects.add(new AfterInvocation(() -> System.out.println("After Method Prototype")));

        final Summer summer = new Summer();
        try {
            summer.bean(IURLRepo.class, (Supplier<IURLRepo>) () -> new URLRepo() , aspects, URLRepo.class.getMethod("testProxy"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        final IURLRepo repo = (IURLRepo) summer.instanceOf(URLRepo.class);
        repo.testProxy();

        assert outContent.toString().contains("After Method Prototype");
        System.setOut(null);
    }

    @Test
    public void afterInvocationSingetonTest() {
        final ArrayList<Aspect> aspects = new ArrayList<>();
        aspects.add(new AfterInvocation(() -> System.out.println("After Method Singleton")));

        final Summer summer = new Summer();
        try {
            summer.bean(IURLRepo.class, new URLRepo() , aspects, URLRepo.class.getMethod("testProxy"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        final IURLRepo repo = (IURLRepo) summer.instanceOf(URLRepo.class);
        repo.testProxy();

        assert outContent.toString().contains("After Method Singleton");
        System.setOut(null);
    }

    @Test
    public void beforeAfterInvocationtest() {
        final ArrayList<Aspect> aspects = new ArrayList<>();
        aspects.add(new BeforeInvocation(() -> System.out.println("Before Method !")));
        aspects.add(new AfterInvocation(() -> System.out.println("After Method !")));

        final Summer summer = new Summer();
        try {
            summer.bean(IURLRepo.class, new URLRepo() , aspects, URLRepo.class.getMethod("testProxy"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        final IURLRepo repo = (IURLRepo) summer.instanceOf(URLRepo.class);
        repo.testProxy();

        assert outContent.toString().contains("After Method !") && outContent.toString().contains("Before Method !");
        System.setOut(null);
    }

    @Test
    public void otherMethodInvocationTest() {
        final ArrayList<Aspect> aspects = new ArrayList<>();
        aspects.add(new BeforeInvocation(() -> System.out.println("Before Method !")));
        aspects.add(new AfterInvocation(() -> System.out.println("After Method !")));

        final Summer summer = new Summer();
        try {
            summer.bean(IURLRepo.class, new URLRepo() , aspects, URLRepo.class.getMethod("testProxy"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        final IURLRepo repo = (IURLRepo) summer.instanceOf(URLRepo.class);
        repo.testProxy2();

        assert !outContent.toString().contains("After Method !") && !outContent.toString().contains("Before Method !");
        System.setOut(null);
    }

}