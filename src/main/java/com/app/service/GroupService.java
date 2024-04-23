package com.app.service;

import java.util.List;

import com.app.dao.GroupDTO;
import com.app.entity.Group;

public interface GroupService {

	List<GroupDTO> getAllGroups();

}
