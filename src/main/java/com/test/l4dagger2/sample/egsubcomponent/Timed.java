package com.test.l4dagger2.sample.egsubcomponent;

import javax.inject.Scope;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Ryan Miao
 * @date 17-10-28
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface Timed {
}