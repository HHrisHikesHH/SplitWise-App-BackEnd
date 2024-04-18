package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Group;

@Repository
public interface GroupDao extends JpaRepository<Group, Integer>{

}
