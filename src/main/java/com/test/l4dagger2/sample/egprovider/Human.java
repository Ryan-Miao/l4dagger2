package com.test.l4dagger2.sample.egprovider;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Ryan Miao
 * @date 17-10-21
 */
public class Human implements Animal{
    private Fruit fruit;

    @Inject
    public Human(@Named("apple") Fruit fruit) {
        this.fruit = fruit;
    }

    @Override
    public String getName() {
        return "一个小孩";
    }

    @Override
    public void eating() {
        System.out.println(getName()+"吃了一个"+fruit.getName());
    }

    public Fruit getFruit() {
        return fruit;
    }
}
