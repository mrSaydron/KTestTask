package ru.mrak.testTask.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mrak.testTask.model.User;
import ru.mrak.testTask.model.UserFields;

import java.text.SimpleDateFormat;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
        logger.info("Add user: " + user);
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
        logger.info("Update user: " + user);
    }

    @Override
    public void removeUser(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, id);
        if(user != null) {
            session.delete(user);
            logger.info("Delete user: " + user);
        } else {
            logger.info("User do not delete: " + id);
        }
    }

    @Override
    public User getUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user;
        //user = (User) session.load(User.class, id);
        List<User> users = session.createSQLQuery("SELECT * FROM user WHERE ID = " + id).addEntity(User.class).list();
        if(users == null || users.isEmpty()) return null;
        return users.get(0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        List<User> users = session.createQuery("from User").list();
        if(users != null) {
            logger.info("Get all users: " + users.size());
        } else {
            logger.info("Do not get all users");
        }
        return users;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers(UserFields sortBy) {
        Session session = sessionFactory.getCurrentSession();
        List<User> users;
        if(sortBy.getDirection().equals("UP")) {
            users = session.createSQLQuery("SELECT * FROM user ORDER BY " + sortBy.getField()).addEntity(User.class).list();
        } else {
            users = session.createSQLQuery("SELECT * FROM user ORDER BY " + sortBy.getField() + " DESC").addEntity(User.class).list();
        }
        //TODO log
        return users;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers(UserFields sortBy, int startLimit, int rowsLimit) {
        Session session = sessionFactory.getCurrentSession();
        List<User> users;
        if(sortBy.getDirection().equals("UP")) {
            users = session.createSQLQuery("SELECT * FROM user ORDER BY " + sortBy.getField() +
                    " LIMIT " + startLimit + ", " + rowsLimit).addEntity(User.class).list();
        } else {
            users = session.createSQLQuery("SELECT * FROM user ORDER BY " + sortBy.getField() + " DESC" +
                    " LIMIT " + startLimit + ", " + rowsLimit).addEntity(User.class).list();
        }
        //TODO log
        return users;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers(UserFields sortBy, int startLimit, int rowsLimit, User findUser) {
        Session session = sessionFactory.getCurrentSession();
        List<User> users;

        String findString = "";
        if(findUser != null) {
            findString = findUser.getId() != null ? "id = " + findUser.getId() : "";
            if(findUser.getName() != null) {
                if(findString.equals("")) findString = "name = '" + findUser.getName() + "'";
                else findString += " AND name = '" + findUser.getName() + "'";
            }
            if(findUser.getAge() != null) {
                if(findString.equals("")) findString = "age = " + findUser.getAge();
                else findString += " AND age = " + findUser.getAge();
            }
            if(findUser.isAdmin() != null) {
                if(findString.equals("")) findString = "isadmin = " + (findUser.isAdmin() ? 1 : 0);
                else findString += " AND isadmin = " + findUser.isAdmin();
            }
            if(findUser.getCreatedDate() != null) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if(findString.equals("")) findString = "createddate = '" + format.format(findUser.getCreatedDate()) + "'";
                else findString += " AND createddate = '" + format.format(findUser.getCreatedDate()) + "'";
            }
            if(!findString.equals("")) findString = "WHERE " + findString;
        }

        String test = "SELECT * FROM USERS " +
                findString +
                " ORDER BY " + sortBy.getField() +
                " LIMIT " + startLimit + ", " + rowsLimit;

        if(sortBy.getDirection().equals("UP")) {
            users = session.createSQLQuery(
                    "SELECT * FROM user " +
                            findString +
                            " ORDER BY " + sortBy.getField() +
                            " LIMIT " + startLimit + ", " + rowsLimit).addEntity(User.class).list();
        } else {
            users = session.createSQLQuery(
                    "SELECT * FROM user " +
                            findString +
                            " ORDER BY " + sortBy.getField() + " DESC" +
                            " LIMIT " + startLimit + ", " + rowsLimit).addEntity(User.class).list();
        }
        //TODO log
        return users;
    }

    @Override
    @SuppressWarnings("unchecked")
    public int getNumberOfRows() {
        Session session = sessionFactory.getCurrentSession();
        List<Integer> list = session.createSQLQuery("SELECT COUNT (*) FROM user").addEntity(Integer.class).list();
        if(list.isEmpty()) return 0;
        return list.get(0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public int getNumberOfFindRows(User findUser) {
        //TODO
        return 0;
    }
}
