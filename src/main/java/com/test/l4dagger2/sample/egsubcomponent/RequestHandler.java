package com.test.l4dagger2.sample.egsubcomponent;

import com.test.l4dagger2.sample.egsubcomponent.server.Loggger;
import com.test.l4dagger2.sample.egsubcomponent.server.Printer;
import com.test.l4dagger2.sample.egsubcomponent.server.Validator;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * subcomponent 测试用例
 * @author Ryan Miao
 * @date 17-10-28
 */
public class RequestHandler {
    private Printer printer;
    private Validator validator;
    private Loggger loggger;
    private ThreadId threadId;

    @Inject
    public RequestHandler(Printer printer, Validator validator, Loggger loggger, ThreadId threadId){
        this.printer = printer;
        this.validator = validator;
        this.loggger = loggger;
        this.threadId = threadId;
    }


    public void writeResponse(int status, String message) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("message", message);
        map.put("status", status);

        if (validator.validate(message)){
            map.put("result", "success");
            loggger.log(map);
            printer.print("status="+status+"; message="+message);
        }else {
            map.put("result", "failed");
            loggger.log(map);
            printer.print("status=400; message = validate failed.");
        }

        printer.print("The threadId is:"+threadId());
    }

    public long threadId(){
        return this.threadId.id();
    }

    public Printer getPrinter() {
        return printer;
    }

    public Validator getValidator() {
        return validator;
    }

    public Loggger getLoggger() {
        return loggger;
    }
}
