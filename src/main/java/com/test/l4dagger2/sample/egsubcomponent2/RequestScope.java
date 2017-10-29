package com.test.l4dagger2.sample.egsubcomponent2;

import javax.inject.Scope;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Ryan Miao
 * @date 17-10-29
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestScope {
}
