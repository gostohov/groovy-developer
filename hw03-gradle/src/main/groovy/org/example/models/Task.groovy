package org.example.models

import java.time.LocalDateTime

class Task {
    String name
    List<Action> actions = []
    LocalDateTime createdAt = LocalDateTime.now()

    Task(String name) {
        this.name = name
    }

    void addAction(Action newAction) {
        // Проверка на перекрытие времени
        actions.each { action ->
            if (isOverlapping(action, newAction)) {
                throw new IllegalArgumentException("Time overlap detected for action: ${newAction.description}")
            }
        }
        actions.add(newAction)
    }

    void removeAction(Action action) {
        actions.remove(action)
    }

    private static boolean isOverlapping(Action existingAction, Action newAction) {
        return !(newAction.endTime.isBefore(existingAction.startTime) || newAction.startTime.isAfter(existingAction.endTime))
    }
}