package com.test.l4dagger2.sample;

import com.test.l4dagger2.sample.egprovider.*;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * @author Ryan Miao
 * @date 17-10-21
 */
public class SampleComponentTest {
    @Test
    public void animal() throws Exception {
        SampleComponent component = DaggerSampleComponent.create();
        Human human = component.animal();
        human.eating();

        Monkey monkey = component.monkey();
        monkey.eating();

        Apple apple = (Apple) human.getFruit();
        Banana banana = (Banana) monkey.getFruit();

        Quality quality1 = apple.getQuality();
        Quality quality2 = banana.getQuality();


        assertEquals(quality1, quality2);
    }

}