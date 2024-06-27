package apap.tutorial.belajarbelajar.service;

import apap.tutorial.belajarbelajar.model.UserModel;

import java.util.List;

public interface UserService {
    UserModel addUser(UserModel user);
    public String encrypt(String password);
    UserModel getUserByUsername(String username);
    List<UserModel> getListUser();
    UserModel deleteUser(UserModel user);
    String updateUserPassword(String username, String password);
}