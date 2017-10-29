package com.test.l4dagger2.sample.egsubcomponent.component;

import com.test.l4dagger2.sample.egsubcomponent.Timed;
import com.test.l4dagger2.sample.egsubcomponent.module.RequestModule;
import com.test.l4dagger2.sample.egsubcomponent.server.RequestFilter;
import dagger.Subcomponent;

/**
 * @author Ryan Miao
 * @date 17-10-29
 */
@Timed
@Subcomponent(modules = RequestModule.class)
public interface ServletComponent {
    RequestFilter requestFilter();

    @Subcomponent.Builder
    interface Builder{
        Builder builder(RequestModule module);
        ServletComponent build();
    }
}
