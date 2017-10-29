package com.test.l4dagger2.sample.egsubcomponent.server;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Ryan Miao
 * @date 17-10-28
 */
@Singleton
public class Validator {
    private Printer printer;

    @Inject
    public Validator(Printer printer) {
        this.printer = printer;
    }

    public boolean validate(String msg){
        if (msg != null){
            printer.print("[Validator]validate pass.");
            return true;
        }else{
            printer.print("[Validator]validate failed.");
            return false;
        }
    }
}
