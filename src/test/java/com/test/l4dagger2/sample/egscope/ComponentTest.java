package com.test.l4dagger2.sample.egscope;

import com.test.l4dagger2.sample.egscope.human.American;
import com.test.l4dagger2.sample.egscope.human.Chinese;
import com.test.l4dagger2.sample.egscope.human.Japanese;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Ryan Miao
 * @date 17-10-25
 */
public class ComponentTest {
    @Test
    public void getAMan() throws Exception {
        AmericanComponent americanComponent = DaggerAmericanComponent.builder().build();
        American american = americanComponent.getAMan();
        Japanese japanese = americanComponent.getAnotherMan();

        Clothes clothes1 = american.getClothes();
        Clothes clothes2 = japanese.getClothes();

        assertEquals(clothes1, clothes2);

        ChineseComponent chineseComponent = DaggerChineseComponent.create();
        Chinese chinese = chineseComponent.getAMan();
        Clothes clothes = chinese.getClothes();

        assertNotEquals(clothes1, clothes);

    }



}