package main;

import org.junit.Test;
import service.IURLRepo;
import service.URLRepo;

import java.util.function.Supplier;

public class SummerTest {

    @Test
    public void providerSingletonTest() throws Exception {
        final Summer summer = new Summer();

        summer.bean(IURLRepo.class, new URLRepo());
        final IURLRepo repo = (IURLRepo) summer.instanceOf(URLRepo.class);

        summer.addScope();

        summer.bean(IURLRepo.class, new URLRepo());
        final IURLRepo repo1 = (IURLRepo) summer.instanceOf(URLRepo.class);

        assert repo == repo1;
    }

    @Test
    public void providerSingletonSameScopeTest() throws Exception {
        final Summer summer = new Summer();

        summer.bean(IURLRepo.class, new URLRepo());
        final IURLRepo repo = (IURLRepo) summer.instanceOf(URLRepo.class);

        summer.bean(IURLRepo.class, new URLRepo());
        final IURLRepo repo1 = (IURLRepo) summer.instanceOf(URLRepo.class);

        assert repo == repo1;
    }

    @Test
    public void providerPrototypeTest() throws Exception {
        final Summer summer = new Summer();

        summer.bean(IURLRepo.class, (Supplier<IURLRepo>) () -> new URLRepo());
        final IURLRepo repo1 = (IURLRepo) summer.instanceOf(URLRepo.class);

        summer.addScope();

        summer.bean(IURLRepo.class, (Supplier<IURLRepo>) () -> new URLRepo());
        final IURLRepo repo2 = (IURLRepo) summer.instanceOf(URLRepo.class);

        assert repo1 != repo2;
    }

    @Test
    public void providerPrototypeSameScope() throws Exception {
        final Summer summer = new Summer();

        summer.bean(IURLRepo.class, (Supplier<IURLRepo>) () -> new URLRepo());
        final IURLRepo repo1 = (IURLRepo) summer.instanceOf(URLRepo.class);

        summer.bean(IURLRepo.class, (Supplier<IURLRepo>) () -> new URLRepo());
        final IURLRepo repo2 = (IURLRepo) summer.instanceOf(URLRepo.class);

        assert repo1 == repo2;
    }
}