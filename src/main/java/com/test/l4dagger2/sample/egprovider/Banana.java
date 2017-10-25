package com.test.l4dagger2.sample.egprovider;

import javax.inject.Inject;

/**
 * @author Ryan Miao
 * @date 17-10-21
 */
public class Banana implements Fruit{
    private Quality quality;

    @Inject
    public Banana(Quality quality) {
        this.quality = quality;
    }

    @Override
    public String getName() {
        return quality.getResult()+"香蕉";
    }

    public Quality getQuality() {
        return quality;
    }
}
