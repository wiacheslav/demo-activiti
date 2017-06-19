package com.example.activiti.dao;


import com.example.activiti.model.ActivitiUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivitiUserRepository extends MongoRepository<ActivitiUser, String> {

    Optional<ActivitiUser> findActivitiUserByUsername(String username);

}
