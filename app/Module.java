import com.google.inject.AbstractModule;
import java.time.Clock;

import services.ApplicationTimer;
import services.AtomicCounter;
import services.Counter;

public class Module extends AbstractModule {

    @Override
    public void configure() {
        bind(Clock.class).toInstance(Clock.systemDefaultZone());
        bind(ApplicationTimer.class).asEagerSingleton();
        bind(Counter.class).to(AtomicCounter.class);
    }

}
