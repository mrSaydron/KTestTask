package ru.mrak.testTask.dao;

import ru.mrak.testTask.model.User;
import ru.mrak.testTask.model.UserFields;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    void updateUser(User user);
    void removeUser(int id);
    User getUserById(int id);

    List<User> getAllUsers();
    List<User> getAllUsers(UserFields sortBy);
    List<User> getUsers(UserFields sortBy, int startLimit, int rowsLimit);
    List<User> getUsers(UserFields sortBy, int startLimit, int rowsLimit, User findUser);

    int getNumberOfRows();
    int getNumberOfFindRows(User findUser);
}
