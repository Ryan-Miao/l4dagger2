package com.test.l4dagger2.sample.egscope;

import com.test.l4dagger2.sample.egscope.human.Chinese;
import dagger.Component;

import javax.inject.Singleton;

/**
 * @author Ryan Miao
 * @date 17-10-25
 */
@Singleton
@Component(modules = ScopeModule2.class)
public interface ChineseComponent {

    Chinese getAMan();
}
