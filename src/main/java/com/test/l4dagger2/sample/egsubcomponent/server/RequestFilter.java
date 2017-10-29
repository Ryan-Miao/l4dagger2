package com.test.l4dagger2.sample.egsubcomponent.server;

import com.test.l4dagger2.sample.egsubcomponent.ThreadId;

import javax.inject.Inject;

/**
 * Parent Component测试用例
 * @author Ryan Miao
 * @date 17-10-28
 */
public class RequestFilter {
    private Loggger loggger;
//    private RequestComponent requestComponent;
    private Validator validator;
    private Printer printer;
    private ThreadId threadId;

    @Inject
    public RequestFilter(Loggger loggger, ThreadId threadId, Validator validator, Printer printer) {
        this.loggger = loggger;
//        this.requestComponent = requestComponentProvider.get().build();
        this.validator = validator;
        this.printer = printer;
        this.threadId = threadId;
    }

    public void filter(String msg){
        if (validator.validate(msg)){
            if (msg.length()>3){
                printer.print("[Filter]The data length is greater than 3, next.");
            }else {
                printer.print("[Filter]The data length is not valid. return.");
            }
        }else {
            printer.print("[Filter]The data is not valid.");

        }
    }
    public Loggger getLoggger() {
        return loggger;
    }

    public ThreadId getThreadId() {
        return threadId;
    }
    //    public RequestComponent getRequestComponent() {
//        return requestComponent;
//    }
}
