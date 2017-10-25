package com.test.l4dagger2.sample.egscope;

import com.test.l4dagger2.sample.egscope.human.American;
import com.test.l4dagger2.sample.egscope.human.Japanese;
import dagger.Component;

import javax.inject.Singleton;

/**
 * @author Ryan Miao
 * @date 17-10-25
 */
@Singleton
@Component(modules = {ScopeModule.class})
public interface AmericanComponent {
    American getAMan();
    Japanese getAnotherMan();
}
