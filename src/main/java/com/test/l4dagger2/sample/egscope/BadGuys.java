package com.test.l4dagger2.sample.egscope;

import javax.inject.Scope;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Ryan Miao
 * @date 17-10-25
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface BadGuys {
}
