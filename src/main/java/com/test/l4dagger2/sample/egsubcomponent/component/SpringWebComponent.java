package com.test.l4dagger2.sample.egsubcomponent.component;

import com.test.l4dagger2.sample.egsubcomponent.module.RequestModule;
import com.test.l4dagger2.sample.egsubcomponent.server.ExceptionHandler;
import com.test.l4dagger2.sample.egsubcomponent.server.RequestInterceptor;
import dagger.Subcomponent;

/**
 * @author Ryan Miao
 * @date 17-10-29
 */
@Subcomponent(modules = RequestModule.class)
public interface SpringWebComponent {

    RequestInterceptor interceptor();
    ExceptionHandler exceptionHandler();

    @Subcomponent.Builder
    interface Builder{
        SpringWebComponent.Builder requestModule(RequestModule module);
        SpringWebComponent build();
    }
}
