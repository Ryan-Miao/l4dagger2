package com.test.l4dagger2.sample.egsubcomponent.module;

import com.test.l4dagger2.sample.egsubcomponent.ThreadId;
import com.test.l4dagger2.sample.egsubcomponent.Timed;
import com.test.l4dagger2.sample.egsubcomponent.server.Printer;
import dagger.Module;
import dagger.Provides;

/**
 * @author Ryan Miao
 * @date 17-10-28
 */
@Module
public class RequestModule {

    @Timed
    @Provides
    public ThreadId threadId(Printer printer){
        return new ThreadId(printer);
    }

}
