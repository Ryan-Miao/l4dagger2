package com.test.l4dagger2.sample.egsubcomponent.component;

import com.test.l4dagger2.sample.egsubcomponent.*;
import com.test.l4dagger2.sample.egsubcomponent.server.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Ryan Miao
 * @date 17-10-28
 */
public class ServerComponentTest {
    private RequestRouter requestRouter;
    private RequestFilter requestFilter;
    private RequestInterceptor interceptor;
    private ExceptionHandler exceptionHandler;

    @Before
    public void setUp(){
        final ServerComponent serverComponent = DaggerServerComponent.builder().build();
        requestRouter = serverComponent.requestRouter();
        requestFilter = serverComponent.servletComponentBuilder().build().requestFilter();
        SpringWebComponent springComponent = serverComponent.springBuilder().build();
        interceptor = springComponent.interceptor();
        exceptionHandler = springComponent.exceptionHandler();
    }

    @Test
    public void testRouter() throws Exception {
        requestRouter.dataReceived("234");
        requestRouter.dataReceived(null);
        RequestComponent requestComponent = requestRouter.getRequestComponent();
        long l = requestComponent.requestHandler().threadId();

        long id = requestRouter.getRequestFilterComponent().requestFilter().getThreadId().id();


    }

    @Test
    public void testFilter(){
        requestFilter.filter("1");
        requestFilter.filter("1234");
    }

    @Test
    public void testParentModuleCouldBeUsedInSubComponent(){
        final Printer printer = requestRouter.getPrinter();
        final Loggger loggger = requestRouter.getLoggger();

        final RequestComponent requestC = requestRouter.getRequestComponent();
        final RequestHandler requestHandler = requestC.requestHandler();

        //证明 parent module 可以在subComponent里使用， 未设置Singleton则默认多例
        assertEquals(printer, requestHandler.getPrinter());
        //证明 未直接声明未子module的bean将会共享给parent module，由parent Component负责
        //如果class没有使用@Singleton标注，则默认生成多例
        assertNotEquals(loggger, requestHandler.getLoggger());
    }

    @Test
    public void testSubComponentModuleCouldOnlyUsedInSubComponent(){
        requestRouter.getRequestComponent().requestHandler().threadId();
    }

    @Test
    public void testSubComponentIsNotSingletonUsedByParentComponentByDefaultScope(){
        final RequestComponent requestComponent = requestRouter.getRequestComponent();
        final RequestComponent requestComponent1 = interceptor.getRequestComponent();
        //说明 Component是通过builder build出来的，每次都是new一个
        assertNotEquals(requestComponent, requestComponent1);

        RequestHandler requestHandler = requestComponent.requestHandler();
        RequestHandler requestHandler1 = requestComponent1.requestHandler();

        //同样， 子组件的Component也是new的。这是默认不指定scope的做法。
        assertNotEquals(requestHandler, requestHandler1);
    }

    @Test
    public void testInterceptor(){
        interceptor.preHandle("msg");
    }
    @Test
    public void testHandleException(){
        exceptionHandler.handleException("msg");
    }

    @Test
    public void testCustomizedScopeUsingTheSameInstanceLikeSingletonByParentComponent(){

    }

}