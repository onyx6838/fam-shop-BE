package com.fam.config.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class OnSendRegistrationUserConfirmViaEmailEvent extends ApplicationEvent {
    private String email;

    public OnSendRegistrationUserConfirmViaEmailEvent(String email) {
        super(email);
        this.email = email;
    }
}
