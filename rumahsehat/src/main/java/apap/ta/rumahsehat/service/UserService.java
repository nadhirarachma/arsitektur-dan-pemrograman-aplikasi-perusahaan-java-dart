package apap.ta.rumahsehat.service;

import apap.ta.rumahsehat.model.UserModel;

public interface UserService {
    UserModel addUser(UserModel user);
    public String encrypt(String password);
    UserModel getUserByUsername(String username);
}