package org.example

import org.example.models.Action
import org.example.models.Event
import org.example.models.Task

import java.time.LocalDateTime

class TodoList {
    List<Task> tasks = []

    void addTask(Task newTask) {
        // Проверка перекрытия времени между задачами
        tasks.each { task ->
            task.actions.each { existingAction ->
                newTask.actions.each { newAction ->
                    if (isOverlapping(existingAction, newAction)) {
                        throw new IllegalArgumentException("Time overlap detected between tasks.")
                    }
                }
            }
        }
        tasks.add(newTask)
    }

    void removeTask(Task task) {
        tasks.remove(task)
    }

    // Получение событий (busy-time) для указанной даты
    List<Event> getBusyTime(LocalDateTime date) {
        return tasks.collectMany { task ->
            task.actions.findAll {
                it.startTime.toLocalDate().isEqual(date.toLocalDate())
            }.collect { action -> new Event(action, action.startTime) }
        }
    }

    // Валидация пересечений времени
    void validateTimeOverlap(Action newAction) {
        def overlappingActions = tasks.collectMany { task ->
            task.actions.findAll { action ->
                (newAction.startTime < action.endTime && newAction.endTime > action.startTime)
            }
        }
        if (!overlappingActions.isEmpty()) {
            throw new IllegalArgumentException("Time overlap detected with existing actions!")
        }
    }

    // Получение задач, сгруппированных по дате
    Map<LocalDateTime, List<Task>> getTasksGroupedByDate() {
        tasks.groupBy { task ->
            task.actions*.startTime*.toLocalDate()
                    .min()
                    .atStartOfDay() // Преобразуем LocalDate в LocalDateTime
        }
    }

    // Форматирование списка задач по дате
    void printTasksByDate(LocalDateTime date) {
        tasks.findAll { task ->
            task.actions.any { action -> action.startTime.toLocalDate().isEqual(date.toLocalDate()) }
        }.each { task ->
            task.with {
                println("Task: $name, Actions: ${actions.size()}")
            }
        }
    }

    private static boolean isOverlapping(Action action1, Action action2) {
        return !(action2.endTime.isBefore(action1.startTime) || action2.startTime.isAfter(action1.endTime))
    }
}
