package com.test.l4dagger2.hello;

import dagger.Module;
import dagger.Provides;

@Module
public class PumpModule {
    @Provides
    Pump providePump(Thermosiphon pump){
        return pump;
    }
}
