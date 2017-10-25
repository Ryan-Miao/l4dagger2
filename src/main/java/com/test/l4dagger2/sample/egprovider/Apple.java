package com.test.l4dagger2.sample.egprovider;

import javax.inject.Inject;

/**
 * @author Ryan Miao
 * @date 17-10-21
 */
public class Apple implements Fruit{
    private Quality quality;

    @Inject
    public Apple(Quality quality) {
        this.quality = quality;
    }

    @Override
    public String getName() {
        return quality.getResult() + "苹果";
    }

    public Quality getQuality() {
        return quality;
    }
}
