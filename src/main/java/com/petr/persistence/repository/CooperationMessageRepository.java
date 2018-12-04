package com.petr.persistence.repository;

import com.petr.persistence.entity.CooperationMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CooperationMessageRepository extends JpaRepository<CooperationMessage,Long> {


}
