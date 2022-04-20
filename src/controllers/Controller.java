package controllers;

import data.Data;
import models.User;
import views.Views;

import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {
    private final Data data;
    private final Views views;

    public Controller(Data data, Views views) {
        this.data = data;
        this.views = views;
    }

    @Override
    public void update(Observable o, Object arg) {
        handleInput((String) arg);
    }

    private void handleInput(String input) {
        switch (input) {
            case "1" -> { createUser(); }
            case "q", "Q" -> { views.kill(); }
        }
    }

    private void createUser() {
        User user = new User(views.getUserName(), views.getPassword());
        System.out.println(user.toString());
    }
}
