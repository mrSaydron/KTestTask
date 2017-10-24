package ru.mrak.testTask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mrak.testTask.model.User;
import ru.mrak.testTask.model.UserFields;
import ru.mrak.testTask.service.UserService;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    private UserService userService;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //List<User> getUsers(UserFields sortBy, int startLimit, int rowsLimit, User findUser);
    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String listUsers(@RequestParam(value = "id", required = false, defaultValue = "") String id,
                            @RequestParam(value = "name", required = false, defaultValue = "") String name,
                            @RequestParam(value = "age", required = false, defaultValue = "") String age,
                            @RequestParam(value = "is_admin", required = false, defaultValue = "") String isAdmin,
                            @RequestParam(value = "created_date", required = false, defaultValue = "") String created_Date,

                            @RequestParam(value = "sort", required = false, defaultValue = "") String sort,
                            @RequestParam(value = "defaultSort", required = false, defaultValue = "ID_UP") String defaultSort,

                            @RequestParam(value = "button", required = false, defaultValue = "") String buttons,
                            @RequestParam(value = "delete", required = false, defaultValue = "") String delete,
                            @RequestParam(value = "edit", required = false, defaultValue = "") String edit,

                            Model model) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String error = "";

        //Определяю поле сортировки
        UserFields sortField = UserFields.ID_UP;
        if(!sort.equals("")) {
            try {
                sortField = UserFields.valueOf(sort);
                defaultSort = sort;
            } catch (IllegalArgumentException e) {
            } catch (NullPointerException e) {}
        } else {
            try {
                sortField = UserFields.valueOf(defaultSort);
                defaultSort = sort;
            } catch (IllegalArgumentException e) {
            } catch (NullPointerException e) {}
        }

        //Удаляю поле
        if(!delete.equals("")) {
            int deleteId;
            try {
                deleteId = Integer.parseInt(delete);
                userService.removeUser(deleteId);
            } catch (NumberFormatException e) {}
        }

        //Добавляю и обнавляю поле
        User user = new User();
        if(!buttons.equals("")) {
            //Создаю user для поиска, создания, редактирования
            if(!id.equals("")) {
                try {
                    user.setId(Integer.parseInt(id));
                } catch (NumberFormatException e) {
                    error += "<br>Invalid data for ID field";
                }
            }
            if(!name.equals("") && name.length() <= 25) {
                user.setName(name);
            }
            if(!age.equals("")) {
                try {
                    user.setAge(Integer.parseInt(age));
                } catch (NumberFormatException e) {
                    error += "<br>Invalid data for the AGE field";
                }
            }
            if(!isAdmin.equals("")) {
                if(isAdmin.equals("true")) user.setAdmin(true);
                else user.setAdmin(false);
            }
            if(!created_Date.equals("")) {
                try {
                    user.setCreatedDate(format.parse(created_Date));
                } catch (ParseException e) {
                    error += "<br>Invalid data for the CREATED DATE field";
                }
            }

            if(buttons.equals("ADD/UPDATE")) {
                if (user.getName() != null && user.getAge() != null && user.isAdmin() != null) {
                    if (user.getId() != null) {
                        User existUser = userService.getUserById(user.getId());
                        if (existUser != null) {
                            user.setCreatedDate(existUser.getCreatedDate());
                            userService.updateUser(user);
                            if(!created_Date.equals("")) error += "<br>Date of user creation is not changed";
                            error += "<br>User ID data = " + user.getId() + " updated";
                        } else error += "<br>User ID = " + user.getId() + " does not exist";
                    } else {
                        user.setCreatedDate(new Date());
                        userService.addUser(user);
                        error += "<br>New user added";
                        if(!created_Date.equals("")) error += "<br>The current date of user creation was used";
                    }
                } else {
                    error += "<br>There is not enough data to create / update a user";
                }
                user = new User();
            }

            if(buttons.equals("RESET FIND")) {
                user = new User();
                id = "";
                name = "";
                age = "";
                isAdmin = "";
                created_Date = "";
            }
        }

        //Редактирование поля
        if(!edit.equals("")) {
            try {
                int userId = Integer.parseInt(edit);
                User editUser = userService.getUserById(userId);
                id = "" + editUser.getId();
                name = editUser.getName();
                age = "" + editUser.getAge();
                isAdmin = "" + editUser.isAdmin();
            } catch (NumberFormatException e) {}
        }

        //Передаю атрибуты в модель
        model.addAttribute("user", new User());
        model.addAttribute("format", format);
        model.addAttribute("id", id);
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        model.addAttribute("is_admin", isAdmin);
        model.addAttribute("created_date", created_Date);
        model.addAttribute("listUsers", userService.getUsers(sortField,
                0,
                100,
                user));
        model.addAttribute("defaultSort", defaultSort);
        model.addAttribute("error", error);

        return "users";
    }
}
