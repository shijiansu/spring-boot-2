package reactive.by.liukang._1_reactor_api;

import reactive.by.liukang._1_reactor_api.EEventSource.EEvent;

public interface EEventListener {
    void onNewEvent(EEvent event);

    void onEventStopped();
}
