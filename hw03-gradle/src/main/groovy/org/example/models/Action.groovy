package org.example.models

import java.time.LocalDateTime

class Action {
    String description
    LocalDateTime startTime
    LocalDateTime endTime

    Action(String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.description = description
        this.startTime = startTime
        this.endTime = endTime
    }
}