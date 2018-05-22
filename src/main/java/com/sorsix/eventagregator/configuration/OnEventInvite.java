package com.sorsix.eventagregator.configuration;

import com.sorsix.eventagregator.model.Event;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OnEventInvite extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */

    private Event event;

    public OnEventInvite(Event event) {
        super(event);
        this.event = event;
    }
}
