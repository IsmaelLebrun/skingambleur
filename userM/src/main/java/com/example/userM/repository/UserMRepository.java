package com.example.userM.repository;

import com.example.user.model.UserM;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMRepository extends MongoRepository<UserM, String> {
}
