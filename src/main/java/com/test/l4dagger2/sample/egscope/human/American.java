package com.test.l4dagger2.sample.egscope.human;

import com.test.l4dagger2.sample.egscope.Clothes;
import com.test.l4dagger2.sample.egscope.Human;

import javax.inject.Inject;

/**
 * @author Ryan Miao
 * @date 17-10-25
 */
public class American implements Human {

    private Clothes clothes;

    @Inject
    public American(Clothes clothes) {
        this.clothes = clothes;
    }

    @Override
    public Clothes getClothes() {

        return clothes;
    }
}
