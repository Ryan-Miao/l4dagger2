package com.test.l4dagger2.sample.egscope;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * @author Ryan Miao
 * @date 17-10-25
 */
@Module
public class ScopeModule2 {

    @Singleton
    @Provides
    Clothes getClothes(){
        return new Trousers();
    }



}
