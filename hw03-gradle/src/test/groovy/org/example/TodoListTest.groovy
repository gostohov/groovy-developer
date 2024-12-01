package org.example

import org.example.models.Action
import org.example.models.Task

import java.time.LocalDateTime
import spock.lang.Specification

class TodoListTest extends Specification {

    def "Test grouping tasks by LocalDateTime"() {
        given: "A todo list with multiple tasks and actions"
        def todoList = new TodoList()
        def task1 = new Task("Task 1")
        def task2 = new Task("Task 2")

        task1.addAction(new Action("Action 1", LocalDateTime.of(2024, 11, 27, 10, 0), LocalDateTime.of(2024, 11, 27, 11, 0)))
        task2.addAction(new Action("Action 2", LocalDateTime.of(2024, 11, 28, 12, 0), LocalDateTime.of(2024, 11, 28, 13, 0)))

        todoList.addTask(task1)
        todoList.addTask(task2)

        when: "Grouping tasks by date"
        def groupedTasks = todoList.getTasksGroupedByDate()

        then: "Tasks are grouped by start of the day as LocalDateTime"
        groupedTasks.size() == 2
        groupedTasks.keySet().contains(LocalDateTime.of(2024, 11, 27, 0, 0))
        groupedTasks.keySet().contains(LocalDateTime.of(2024, 11, 28, 0, 0))
        groupedTasks[LocalDateTime.of(2024, 11, 27, 0, 0)].size() == 1
        groupedTasks[LocalDateTime.of(2024, 11, 28, 0, 0)].size() == 1
    }

    def "Test busy time retrieval for a specific date"() {
        given: "A todo list with tasks"
        def todoList = new TodoList()
        def task = new Task("Busy Task")
        def action = new Action("Action", LocalDateTime.of(2024, 11, 27, 9, 0), LocalDateTime.of(2024, 11, 27, 10, 0))
        task.addAction(action)
        todoList.addTask(task)

        when: "Retrieving busy time for a specific date"
        def events = todoList.getBusyTime(LocalDateTime.of(2024, 11, 27, 0, 0))

        then: "Busy time should match the task's action"
        events.size() == 1
        events[0].action.description == "Action"
        events[0].timestamp == LocalDateTime.of(2024, 11, 27, 9, 0)
    }

    def "Test time overlap validation"() {
        given: "A todo list with tasks"
        def todoList = new TodoList()
        def task1 = new Task("Task 1")
        def action1 = new Action("Action 1", LocalDateTime.of(2024, 11, 27, 9, 0), LocalDateTime.of(2024, 11, 27, 10, 0))
        task1.addAction(action1)
        todoList.addTask(task1)

        when: "Adding a task with overlapping action"
        def task2 = new Task("Task 2")
        def action2 = new Action("Action 2", LocalDateTime.of(2024, 11, 27, 9, 30), LocalDateTime.of(2024, 11, 27, 10, 30))
        task2.addAction(action2)
        todoList.addTask(task2)

        then: "An exception is thrown due to time overlap"
        thrown(IllegalArgumentException)
    }

    def "Test printing tasks by date"() {
        given: "A todo list with tasks on the same day"
        def todoList = new TodoList()
        def task1 = new Task("Task 1")
        def task2 = new Task("Task 2")

        task1.addAction(new Action("Action 1", LocalDateTime.of(2024, 11, 27, 10, 0), LocalDateTime.of(2024, 11, 27, 11, 0)))
        task2.addAction(new Action("Action 2", LocalDateTime.of(2024, 11, 27, 12, 0), LocalDateTime.of(2024, 11, 27, 13, 0)))

        todoList.addTask(task1)
        todoList.addTask(task2)

        when: "Printing tasks for a specific date"
        todoList.printTasksByDate(LocalDateTime.of(2024, 11, 27, 0, 0))

        then: "Tasks are printed correctly"
        // Проверяем, что метод срабатывает без ошибок. Вывод в консоль не тестируем.
        notThrown(Exception)
    }
}
