package solerasports.server.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import solerasports.server.models.User;
import solerasports.server.models.enums.UserType;
import solerasports.server.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Map<Boolean, String> register(User user) {
        Map<Boolean, String> res = new HashMap<Boolean, String>();
        if (userExists(user)) {
            res.put(false, "User already exists");
        } else if (!areCredentialsValid(user.getEmail(), user.getPassword())) {
            res.put(false, "Invalid credentials");
        }
        if (res.size() == 0) {
            res.put(true, "User registered successfully");
            if(user.getUserType()==null) {
                user.setUserType(UserType.USER);
            }
            userRepository.save(user);
        }
        return res;
    }

    //Auxiliar methods

    public Boolean userExists(User user) {
        for (User currentUser : userRepository.findAll()) {
            if (currentUser.getEmail().equals(user.getEmail())) {
                return true;
            }
        }
        return false;
    }

    public Boolean areCredentialsValid(String email, String password) {
        Boolean res = false;
        if (emailValid(email)
                && passwordValid(password)) {
            res = true;
        }
        return res;
    }

    public Boolean emailValid(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public Boolean passwordValid(String password) {
        Boolean flagUpper = false;
        Boolean flagSpecial = false;
        Boolean flagNum = false;
        /*for (int i = 0; i < password.length(); i++) {
            if (passwordHasUppercase(password.charAt(i))) {
                flagUpper = true;
            } else if (passwordHasNumber(password.charAt(i))) {
                flagNum = true;
            } else if (passwordHasSpecialChar(password.charAt(i))) {
                flagSpecial = true;
            }
        }*/
        if (!(flagNum && flagSpecial && flagUpper && passwordHasProperSize(password))) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean passwordHasUppercase(Character c) {
        return Character.isUpperCase(c);
    }

    public Boolean passwordHasNumber(Character c) {
        return Character.isDigit(c);
    }

    public Boolean passwordHasSpecialChar(Character c) {
        return !Character.isDigit(c)
                && !Character.isLetter(c)
                && !Character.isWhitespace(c);
    }

    public Boolean passwordHasProperSize(String password) {
        return password.length() > 7 && password.length() < 16;
    }


}
