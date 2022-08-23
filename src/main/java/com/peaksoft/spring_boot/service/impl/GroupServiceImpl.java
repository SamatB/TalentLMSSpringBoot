package com.peaksoft.spring_boot.service.impl;

import com.peaksoft.spring_boot.dto.GroupRequest;
import com.peaksoft.spring_boot.dto.GroupResponse;
import com.peaksoft.spring_boot.entity.Course;
import com.peaksoft.spring_boot.entity.Group;
import com.peaksoft.spring_boot.repository.CourseRepository;
import com.peaksoft.spring_boot.repository.GroupRepository;
import com.peaksoft.spring_boot.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    private final CourseRepository courseRepository;

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public Group getGroupByName(String name) {
        return groupRepository.findByGroupName(name);
    }

    @Override
    public GroupResponse create(GroupRequest request) {
        Group group = new Group();
        group.setGroupName(request.getName());
        group.setDateOfCreate(request.getDateOfStart());
        group.setDateOfFinish(request.getDateOfFinish());
        Course course = courseRepository.findById(request.getCourseId()).get();
        List<Course> courses = new ArrayList<>();
        courses.add(course);
        group.setCourses(courses);
        groupRepository.save(group);
        return mapToResponse(group);
    }

    @Override
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public GroupResponse update(Long id, GroupRequest request) {
        Group group = groupRepository.findById(id).get();
        group.setGroupName(request.getName());
        group.setDateOfCreate(request.getDateOfStart());
        group.setDateOfFinish(request.getDateOfFinish());
//        group.setCourses(request.getCourseId());
        groupRepository.save(group);
        return mapToResponse(group);
    }

    private GroupResponse mapToResponse(Group group) {
        GroupResponse groupResponse = new GroupResponse();
        groupResponse.setName(group.getGroupName());
        groupResponse.setDateOfStart(group.getDateOfCreate());
        groupResponse.setDateOfFinish(group.getDateOfFinish());
        return groupResponse;
    }
}
