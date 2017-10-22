package com.test.l4dagger2.hello;

import dagger.Component;
import dagger.Reusable;

import javax.inject.Singleton;

/**
 * lio create this.
 *
 * The hello world of dagger2.
 *
 * reference: https://google.github.io/dagger/users-guide
 */

/**
 * @author Ryan Miao
 */
public class CoffeeApp {
    @Singleton
    @Component(modules = { DripCoffeeModule.class })
    public interface CoffeeShop {
        CoffeeMaker maker();
    }


}
