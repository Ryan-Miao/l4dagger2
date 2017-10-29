package com.test.l4dagger2.sample.egsubcomponent.module;

import com.test.l4dagger2.sample.egsubcomponent.component.ServletComponent;
import com.test.l4dagger2.sample.egsubcomponent.component.SpringWebComponent;
import com.test.l4dagger2.sample.egsubcomponent.server.Printer;
import com.test.l4dagger2.sample.egsubcomponent.component.RequestComponent;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * 父Component的module， 既可以在父Component里使用，也可以在subComponent里使用。
 *
 * @author Ryan Miao
 * @date 17-10-28
 */
@Module(subcomponents = {
        RequestComponent.class,
        ServletComponent.class,
        SpringWebComponent.class
})
public class ServerModule {
    @Provides
    @Singleton
    public Printer printer() {
        return new Printer();
    }
}
