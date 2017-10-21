package com.test.l4dagger2.hello;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module(includes = PumpModule.class)
class DripCoffeeModule {
    @Provides
    @Singleton
    Heater provideHeater() {
        return new ElectricHeater();
    }
}
