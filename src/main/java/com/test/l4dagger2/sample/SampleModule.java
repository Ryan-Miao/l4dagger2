package com.test.l4dagger2.sample;

import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * @author Ryan Miao
 * @date 17-10-21
 */
@Module
public class SampleModule {

    @Provides
    @Singleton
    static Quality provideQuality(){
        return new Size();
    }

    @Provides
    @Named("apple")
    static Fruit provideApple(Apple apple){
        return apple;
    }

    @Provides
    @Named("banana")
    static Fruit provideBanana(Banana banana){
        return banana;
    }


}
