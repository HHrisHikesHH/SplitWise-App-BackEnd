package com.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.GroupDTO;
import com.app.dao.GroupDao;
import com.app.entity.Group;

@Service
@Transactional
public class GroupServiceImpl implements GroupService{

	@Autowired
	private GroupDao groupDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<GroupDTO> getAllGroups() {
		
		List<GroupDTO> res = new ArrayList<>();
		List<Group> groups = groupDao.findAll();
		groups.forEach(g -> {
			GroupDTO  gdto = new GroupDTO();
			List<Integer> gmemId = new ArrayList<>();
			List<String> gmembName = new ArrayList<>();
			
			gdto.setGroupId(g.getGroupId());
			gdto.setGroupName(g.getGroupName()); 
			
			g.getMembers().forEach(m -> {
				gmemId.add(m.getUserId());
				gmembName.add(String.format("%s %s", m.getFirstName(), m.getLastName()));
			});
			
			gdto.setGroupmemberID(gmemId);
			gdto.setGroupmemberNames(gmembName);
			res.add(gdto);
		});
		
		return res;
	}

}
