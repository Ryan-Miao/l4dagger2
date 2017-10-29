package com.test.l4dagger2.sample.egsubcomponent2;

import dagger.Subcomponent;

/**
 * @author Ryan Miao
 * @date 17-10-29
 */
@SessionScope @Subcomponent
interface SessionComponent {
    FooRequestComponent.Builder fooRequestComponent();
    BarRequestComponent.Builder barRequestComponent();

    @Subcomponent.Builder
    interface Builder {
        SessionComponent build();
    }
}
