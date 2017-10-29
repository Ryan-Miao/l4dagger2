package com.test.l4dagger2.sample.egsubcomponent.server;

import com.test.l4dagger2.sample.egsubcomponent.component.RequestComponent;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/**
 * Parent Component测试用例
 * @author Ryan Miao
 * @date 17-10-28
 */
@Singleton
public class ExceptionHandler {
    private Loggger loggger;
    private RequestComponent requestComponent;
    private Validator validator;
    private Printer printer;

    @Inject
    public ExceptionHandler(Loggger loggger, Provider<RequestComponent.Builder> requestComponentProvider, Validator validator, Printer printer) {
        this.loggger = loggger;
        this.requestComponent = requestComponentProvider.get().build();
        this.validator = validator;
        this.printer = printer;
    }

    public void handleException(String msg){
        printer.print("[ExceptionHandler]Handle the exception,"+msg);
    }

    public Loggger getLoggger() {
        return loggger;
    }

    public RequestComponent getRequestComponent() {
        return requestComponent;
    }

    public Validator getValidator() {
        return validator;
    }

    public Printer getPrinter() {
        return printer;
    }
}
