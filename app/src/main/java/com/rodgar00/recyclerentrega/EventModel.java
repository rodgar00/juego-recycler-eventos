package com.rodgar00.recyclerentrega;

public class EventModel {
    public String eventName;
    public String eventDate;

    // Nuevo atributo
    public int estado = 0; // 0 = normal, 1 = acierto, 2 = fallo

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
