package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bank {
    private final List<User> users = new ArrayList<User>();

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public Optional<User> getUserByUsername(String username) {
        return users.stream().filter(u -> u.username.equals(username)).findFirst();
    }

    public Optional<SavingsAccount> getAccountWithId(int id) {
        Optional<User> optionalUser = users.stream().filter(u -> u.account.getId() == id).findFirst();
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return Optional.ofNullable(user.getBankAccount());
        }
        return Optional.empty();
    }
}
