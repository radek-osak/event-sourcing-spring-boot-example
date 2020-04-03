package com.ara.es.es.events;

import com.ara.es.es.BaseEvent;
import com.ara.es.utils.Status;

public class AccountActivatedEvent extends BaseEvent<String> {

    public final Status status;

    public AccountActivatedEvent(String id, Status status) {
        super(id);
        this.status = status;
    }
}
