package solerasports.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import solerasports.server.services.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

}
