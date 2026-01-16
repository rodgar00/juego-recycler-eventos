package com.rodgar00.recyclerentrega;

public class EventModel {
    public String eventName;
    public String eventDate;

    private int failCount = 0;
    public int estado = 0;

    public int getFailCount() {
        return failCount;
    }

    public void incrementFailCount() {
        if (failCount < 5) failCount++;
    }

    public EventModel(String eventName, String eventDate) {
        this.eventName = eventName;
        this.eventDate = eventDate;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDate() {
        return eventDate;
    }
}
