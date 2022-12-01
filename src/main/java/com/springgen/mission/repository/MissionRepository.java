package com.springgen.mission.repository;

import com.springgen.mission.dto.Mission;
import java.lang.Integer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends MongoRepository<Mission, Integer> {
}
