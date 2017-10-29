package com.test.l4dagger2.sample.egsubcomponent;

import com.test.l4dagger2.sample.egsubcomponent.server.Printer;
import dagger.Reusable;

/**
 * @author Ryan Miao
 * @date 17-10-28
 */
@Reusable
public class ThreadId {
    private Printer printer;
    private long id;

    public ThreadId(Printer printer) {
        this.printer = printer;
        this.id = System.currentTimeMillis();
    }

    public long id(){
        printer.print(this.id + "");
        return this.id;
    }
}
