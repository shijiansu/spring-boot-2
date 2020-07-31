package reactive.by.liukang._1_reactor_api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class EEventSource {

  private List<EEventListener> listeners;

  public EEventSource() {
    this.listeners = new ArrayList<>();
  }

  public void register(EEventListener listener) {
    listeners.add(listener);
  }

  public void newEvent(EEvent event) {
    for (EEventListener listener : listeners) {
      listener.onNewEvent(event);
    }
  }

  public void eventStopped() {
    for (EEventListener listener : listeners) {
      listener.onEventStopped();
    }
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class EEvent {
    private Date timeStemp;
    private String message;
  }
}
