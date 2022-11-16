package apap.ta.rumahsehat.service;

import apap.ta.rumahsehat.model.UserModel;
import apap.ta.rumahsehat.repository.UserDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDb userDb;

    @Override
    public UserModel addUser(UserModel user) {

        if (user.getPassword().equals("rumahsehat") || user.getPassword().matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")) {
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
}
