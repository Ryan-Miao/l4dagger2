package com.test.l4dagger2.sample.egsubcomponent2;

import dagger.Component;

import javax.inject.Singleton;

/**
 * @author Ryan Miao
 * @date 17-10-29
 */
@Singleton
@Component
public interface RootComponent {
    SessionComponent.Builder sessionComponent();
}
