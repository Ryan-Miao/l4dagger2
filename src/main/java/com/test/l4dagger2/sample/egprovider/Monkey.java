package com.test.l4dagger2.sample.egprovider;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Ryan Miao
 * @date 17-10-21
 */
public class Monkey implements Animal{
    private Fruit fruit;

    @Inject
    public Monkey(@Named("banana") Fruit fruit) {
        this.fruit = fruit;
    }

    @Override
    public String getName() {
        return "这是一只金丝猴.";
    }

    @Override
    public void eating() {
        System.out.println(getName()+"它正在吃"+fruit.getName());
    }

    public Fruit getFruit() {
        return fruit;
    }
}
