package ach.hin.data.repository.log;

import org.springframework.data.mongodb.repository.MongoRepository;

import ach.hin.data.entity.log.AccessLog;

public interface AccessLogRepository extends MongoRepository<AccessLog, String> {

}
