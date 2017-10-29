package com.test.l4dagger2.sample.egsubcomponent.component;

import com.test.l4dagger2.sample.egsubcomponent.RequestHandler;
import com.test.l4dagger2.sample.egsubcomponent.Timed;
import com.test.l4dagger2.sample.egsubcomponent.module.RequestModule;
import dagger.Subcomponent;

/**
 * @author Ryan Miao
 * @date 17-10-28
 */
@Timed
@Subcomponent(modules = RequestModule.class)
public interface RequestComponent {
    RequestHandler requestHandler();

    @Subcomponent.Builder
    interface Builder{
        Builder requestModule(RequestModule module);
        RequestComponent build();
    }
}
