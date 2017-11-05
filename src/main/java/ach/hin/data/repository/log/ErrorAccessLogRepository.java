package ach.hin.data.repository.log;

import org.springframework.data.mongodb.repository.MongoRepository;

import ach.hin.data.entity.log.ErrorAccessLog;

public interface ErrorAccessLogRepository extends MongoRepository<ErrorAccessLog, String>{

}
