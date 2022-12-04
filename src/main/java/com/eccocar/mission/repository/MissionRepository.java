package com.eccocar.mission.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.eccocar.mission.dao.MissionDAO;

@Repository
public interface MissionRepository extends MongoRepository<MissionDAO, UUID> {
    public abstract Optional<MissionDAO> findByName(String name);
}
