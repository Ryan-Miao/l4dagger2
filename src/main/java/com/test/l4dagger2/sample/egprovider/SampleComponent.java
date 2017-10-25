package com.test.l4dagger2.sample.egprovider;

import dagger.Component;

import javax.inject.Singleton;

/**
 * @author Ryan Miao
 * @date 17-10-21
 */
@Singleton
@Component(modules = {SampleModule.class})
public interface SampleComponent {
    Human animal();
    Monkey monkey();

}
