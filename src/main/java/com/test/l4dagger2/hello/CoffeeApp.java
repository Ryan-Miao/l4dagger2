package com.test.l4dagger2.hello;

import dagger.Component;

import javax.inject.Singleton;

/**
 * lio create this.
 *
 * The hello world of dagger2.
 *
 * reference: https://google.github.io/dagger/users-guide
 */
public class CoffeeApp {
    @Singleton
    @Component(modules = { DripCoffeeModule.class })
    public interface CoffeeShop {
        CoffeeMaker maker();
    }

    public static void main(String[] args) {
        CoffeeShop coffeeShop = DaggerCoffeeApp_CoffeeShop.builder().build();
        coffeeShop.maker().brew();
    }
}
