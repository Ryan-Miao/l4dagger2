package com.test.l4dagger2.hello;

import dagger.Binds;
import dagger.Module;

@Module
abstract class PumpModule {
    @Binds
    abstract Pump providePump(Thermosiphon pump);
}
