package org.user.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.user.app.model.User;

public interface UserRepository extends JpaRepository <User, Long> {

}
