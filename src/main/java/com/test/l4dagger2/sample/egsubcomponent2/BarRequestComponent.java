package com.test.l4dagger2.sample.egsubcomponent2;

import dagger.Subcomponent;

/**
 * @author Ryan Miao
 * @date 17-10-29
 */
@RequestScope @Subcomponent
public interface BarRequestComponent {
    @Subcomponent.Builder
    interface Builder {
        BarRequestComponent build();
    }
}
