package solerasports.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import solerasports.server.models.User;

public interface UserRepository extends JpaRepository<User, Long> {  }
