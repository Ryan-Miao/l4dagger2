package com.test.l4dagger2.sample.egsubcomponent.server;

import javax.inject.Inject;
import java.util.Map;

/**
 * @author Ryan Miao
 * @date 17-10-28
 */
public class Loggger {
    private Printer printer;

    @Inject
    public Loggger(Printer printer) {
        this.printer = printer;
    }

    public void log(Map<String, Object> data){
        printer.print("log data="+data.toString());
    }
}
