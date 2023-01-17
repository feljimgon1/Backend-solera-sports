package solerasports.server.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import solerasports.server.models.User;
import solerasports.server.services.UserService;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "api/v1/users", method = RequestMethod.GET)
    private ResponseEntity<?> getUsers() throws Exception{
        Map<String, List<User>> res = new HashMap<>();
        try {
            List<User> aux = userService.getUsers();
            String msg;
            if (aux.isEmpty()) {
                msg = "There are not users at the moment. Let's add some!";
                res.put(msg, aux);
                return new ResponseEntity<>(res, HttpStatus.OK);
            } else {
                msg = "Users returned successfully!";
                res.put(msg, aux);
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
        } catch (Exception e) {
            throw new Exception("Oops! Users could not be recovered");
        }
    }

    @RequestMapping(value = "api/v1/auth/register", method = RequestMethod.POST)
    private ResponseEntity<?> register(@RequestBody User user) throws Exception {
        Map<String, String> res = new HashMap<>();
        Map<Boolean, String> aux = userService.register(user);
        if ((boolean) aux.keySet().toArray()[0] != false) {
            res.put("msg", (String) aux.values().toArray()[0]);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            res.put("msg", (String) aux.values().toArray()[0]);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }

}
