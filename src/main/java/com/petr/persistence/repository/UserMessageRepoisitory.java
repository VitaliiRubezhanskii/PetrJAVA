package com.petr.persistence.repository;


import com.petr.persistence.entity.UserMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMessageRepoisitory extends JpaRepository<UserMessage,Long> {


}
