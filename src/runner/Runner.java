package runner;

import controllers.Controller;
import data.Data;
import models.BankAccount;
import models.User;
import views.Views;

public class Runner {
    public static void main(String[] args) {
        Views views = new Views();
        Controller controller = new Controller(new Data(), views);

        views.addObserver(controller);
        views.run();
    }
}
