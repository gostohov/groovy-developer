package org.example.models

import java.time.LocalDateTime

class Event {
    Action action
    LocalDateTime timestamp

    Event(Action action, LocalDateTime timestamp) {
        this.action = action
        this.timestamp = timestamp
    }

    @Override
    String toString() {
        return "Event: ${action.description} at ${timestamp}"
    }
}