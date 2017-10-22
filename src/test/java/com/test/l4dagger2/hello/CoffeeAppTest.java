package com.test.l4dagger2.hello;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Ryan Miao
 * @date 17-10-22
 */
public class CoffeeAppTest {
    @Test
    public void testInject() {
        CoffeeApp.CoffeeShop coffeeShop = DaggerCoffeeApp_CoffeeShop.builder().build();
        CoffeeMaker maker = coffeeShop.maker();
        CoffeeMaker maker1 = coffeeShop.maker();
        assertNotEquals(maker , maker1);

        CoffeeApp.CoffeeShop coffeeShop2 = DaggerCoffeeApp_CoffeeShop.builder().build();
        CoffeeMaker maker2 = coffeeShop.maker();

        assertNotEquals(coffeeShop, coffeeShop2);

        assertNotEquals(maker , maker2);

        //单例的元素
        assertEquals(maker.getHeater() , maker1.getHeater());

        //没有设定单例
        assertNotEquals(maker.getPump() , maker1.getPump());

        //单例的元素
        assertEquals(maker.getHeater() , maker2.getHeater());

        //没有设定单例
        assertNotEquals(maker.getPump(), maker2.getPump());
    }
}