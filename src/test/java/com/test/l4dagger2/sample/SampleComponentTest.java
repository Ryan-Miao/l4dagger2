package com.test.l4dagger2.sample;

import org.junit.Test;

/**
 * @author Ryan Miao
 * @date 17-10-21
 */
public class SampleComponentTest {
    @Test
    public void animal() throws Exception {
        SampleComponent component = DaggerSampleComponent.create();
        component.animal().eating();

        component.monkey().eating();
    }

}