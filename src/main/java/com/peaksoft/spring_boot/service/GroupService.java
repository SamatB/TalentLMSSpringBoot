package com.peaksoft.spring_boot.service;

import com.peaksoft.spring_boot.dto.GroupRequest;
import com.peaksoft.spring_boot.dto.GroupResponse;
import com.peaksoft.spring_boot.entity.Course;
import com.peaksoft.spring_boot.entity.Group;

import java.util.List;

public interface GroupService {

    List<Group> getAllGroups();
    Group getGroupByName(String name);
    GroupResponse create(GroupRequest request);
    GroupResponse update(Long id, GroupRequest request);
    void deleteById(Long id);
}
