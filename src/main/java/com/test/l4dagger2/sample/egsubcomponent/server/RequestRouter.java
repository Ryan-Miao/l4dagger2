package com.test.l4dagger2.sample.egsubcomponent.server;

import com.test.l4dagger2.sample.egsubcomponent.component.RequestComponent;
import com.test.l4dagger2.sample.egsubcomponent.component.ServletComponent;
import com.test.l4dagger2.sample.egsubcomponent.component.SpringWebComponent;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

/**
 * Parent Component测试用例
 * @author Ryan Miao
 * @date 17-10-28
 */
@Singleton
public class RequestRouter {
    private Printer printer;
    private Loggger loggger;
    private RequestComponent requestComponent;
    private ServletComponent requestFilterComponent;
    private SpringWebComponent springWebComponent;
    @Inject
    RequestRouter(
            Printer printer, Provider<RequestComponent.Builder> requestComponentProvider, Loggger loggger,
            Provider<ServletComponent.Builder> servletComponentBuilder,
            Provider<SpringWebComponent.Builder> springWebComponentProvier) {
        this.printer = printer;
        this.requestComponent =
                requestComponentProvider.get()
                        .build();
        this.loggger = loggger;
        this.requestFilterComponent = servletComponentBuilder.get().build();
        this.springWebComponent = springWebComponentProvier.get().build();
    }

    public void dataReceived(String data) {
        printer.print("[Router]received data: "+data);
        requestFilterComponent.requestFilter().filter(data);

        springWebComponent.interceptor().preHandle(data);
        requestComponent.requestHandler()
                .writeResponse(200, data);
        springWebComponent.exceptionHandler().handleException(data);

        Map<String, Object> map = new HashMap<>();
        map.put("data", data);
        map.put("step", "router end.");
        loggger.log(map);
        printer.print("--------------------------------");
    }

    public Printer getPrinter() {
        return printer;
    }

    public RequestComponent getRequestComponent() {
        return requestComponent;
    }

    public ServletComponent getRequestFilterComponent() {
        return requestFilterComponent;
    }

    public Loggger getLoggger() {
        return loggger;
    }
}
