package com.test.l4dagger2.sample.egsubcomponent.component;

import com.test.l4dagger2.sample.egsubcomponent.module.ServerModule;
import com.test.l4dagger2.sample.egsubcomponent.server.RequestRouter;
import dagger.Component;

import javax.inject.Singleton;

/**
 * @author Ryan Miao
 * @date 17-10-28
 */
@Singleton
@Component(modules = ServerModule.class)
public interface ServerComponent {
    RequestRouter requestRouter();
    RequestComponent.Builder requestComponentBuilder();
    ServletComponent.Builder servletComponentBuilder();
    SpringWebComponent.Builder springBuilder();
}