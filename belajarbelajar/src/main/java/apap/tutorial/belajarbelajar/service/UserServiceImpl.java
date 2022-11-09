package apap.tutorial.belajarbelajar.service;

import apap.tutorial.belajarbelajar.model.UserModel;
import apap.tutorial.belajarbelajar.repository.UserDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDb userDb;

    @Override
    public UserModel addUser(UserModel user) {

        if (user.getPassword().matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")) {
            String pass = encrypt(user.getPassword());

            user.setPassword(pass);
            return userDb.save(user);
        }

        return new UserModel();
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public UserModel getUserByUsername(String username){
        UserModel user = userDb.findByUsername(username);
        return user;
    }

    @Override
    public List<UserModel> getListUser() {
        return userDb.findAll();
    }

    @Override
    public UserModel deleteUser(UserModel user) {
        userDb.delete(user);
        return user;
    }

    @Override
    public String updateUserPassword(String username, String password) {
        UserModel user = userDb.findByUsername(username);
        
        if (password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")) {
            String pass = encrypt(password);

            user.setPassword(pass);
            userDb.save(user);

            return "success";
        }

        return "tidak sesuai ketentuan";
    }
}
