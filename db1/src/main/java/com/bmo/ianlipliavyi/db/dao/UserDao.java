package com.bmo.ianlipliavyi.db.dao;

import com.bmo.ianlipliavyi.db.model.UserDBModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends CrudRepository<UserDBModel, Long> {

    @Query("SELECT m FROM UserDBModel m WHERE LOWER(name) like concat(:search, '%') OR LOWER(last) like concat(:search, '%')")
    List<UserDBModel> searchFirstChars(String search);

}
