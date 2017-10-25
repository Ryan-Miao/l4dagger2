package com.test.l4dagger2.sample.egscope;

import java.util.Random;

/**
 * @author Ryan Miao
 * @date 17-10-25
 */
public class Trousers implements Clothes{
    @Override
    public void wear() {
        System.out.println(this.hashCode());
    }
}
