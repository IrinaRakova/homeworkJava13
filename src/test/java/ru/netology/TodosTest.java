package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindQueryInSimpleTask() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        boolean expected = true;
        boolean actual = simpleTask.matches("родителям");

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void shouldNotFindQueryInSimpleTask() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        boolean expected = false;
        boolean actual = simpleTask.matches("брату");

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void shouldFindQueryInEpic() {
        String[] subtasks = {"Огурец", "Помидор", "Баклажан", "Капуста"};
        Epic epic = new Epic(10, subtasks);

        boolean expected = true;
        boolean actual = epic.matches("Помидор");

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void shouldNotFindQueryInSimpleEpic() {
        String[] subtasks = {"Огурец", "Помидор", "Баклажан", "Капуста"};
        Epic epic = new Epic(10, subtasks);

        boolean expected = false;
        boolean actual = epic.matches("Лук");

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void shouldFindQueryInMeeting() {
        Meeting meeting = new Meeting(
                349,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        boolean expected = true;
        boolean actual = meeting.matches("Выкатка");

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void shouldNotFindQueryInMeeting() {
        Meeting meeting = new Meeting(
                349,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        boolean expected = false;
        boolean actual = meeting.matches("2й");

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void shouldFindOneQueryInThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {epic};
        Task[] actual = todos.search("Молоко");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindTwoQueryInThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"родителям", "брату", "бабушке"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic};
        Task[] actual = todos.search("родителям");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindThreeQueryInThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Выкатка приложения НетоБанка");

        String[] subtasks = {"Приложение НетоБанка", "Приложение ресторана", "Приложение супермаркета"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.search("НетоБанка");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindQueryInThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Выкатка приложения НетоБанка");

        String[] subtasks = {"Приложение НетоБанка", "Приложение Банка", "Приложение Нето"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {};
        Task[] actual = todos.search("Небанк");
        Assertions.assertArrayEquals(expected, actual);
    }
}
