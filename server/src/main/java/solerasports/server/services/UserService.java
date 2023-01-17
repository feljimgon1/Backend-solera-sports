package solerasports.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import solerasports.server.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

}
