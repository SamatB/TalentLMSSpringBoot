package com.peaksoft.spring_boot.service.impl;

import com.peaksoft.spring_boot.dto.StudentRequest;
import com.peaksoft.spring_boot.dto.StudentResponse;
import com.peaksoft.spring_boot.dto.TeacherRequest;
import com.peaksoft.spring_boot.dto.TeacherResponse;
import com.peaksoft.spring_boot.entity.*;
import com.peaksoft.spring_boot.repository.CourseRepository;
import com.peaksoft.spring_boot.repository.GroupRepository;
import com.peaksoft.spring_boot.repository.RoleRepository;
import com.peaksoft.spring_boot.repository.UserRepository;
import com.peaksoft.spring_boot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final GroupRepository groupRepository;

    private final CourseRepository courseRepository;

    private final PasswordEncoder passwordEncoder;


    @Override
    public StudentResponse getStudentById(Long id) {
        User user = userRepository.findById(id).get();
        User user1 = null;
        for (int i = 0; i < user.getRoles().size(); i++) {
            if (user.getRoles().get(i).getName().equals("STUDENT")) {
                user1 = user;
            } else if (user1 == null) {
                System.out.println(("User with id:{} not found" + id));
            }
        }
        return mapToStudentResponse(user1);
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        List<User> users = userRepository.getAllByRole("STUDENT");
        List<StudentResponse> responses = new ArrayList<>();
        for (User user : users) {
            StudentResponse response = mapToStudentResponse(user);
            responses.add(response);
        }
        return responses;
    }

    @Override
    public List<TeacherResponse> getAllTeachers() {
        List<User> users = userRepository.findAll();
        List<TeacherResponse> responses = new ArrayList<>();
        TeacherResponse response = new TeacherResponse();
        for (User user : users) {
            List<Role> roles = user.getRoles();
            for (int i = 0; i < roles.size(); i++) {
                if (user.getRoles().get(i).getName().equals("TEACHER")) {
                    response.setEmail(user.getEmail());
                    response.setFirstName(user.getUsername());
                    response.setLastname(user.getUserLastname());
                    response.setCourse(user.getCourse());
                    responses.add(response);
                }
            }
        }
        return responses;
    }

    @Override
    public StudentResponse getStudentByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return mapToStudentResponse(user);
    }

    @Override
    public TeacherResponse getTeacherByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return mapToTeacherResponse(user);
    }

    @Override
    public StudentResponse register(StudentRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUserName(request.getName());
        user.setUserLastname(request.getSurname());
        user.setCreated(LocalDate.now());
        user.setStatus(Status.ACTIVE);
        user.setStudyFormat(StudyFormat.valueOf(request.getStudyFormat()));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("STUDENT"));
        user.setRoles(roles);
        userRepository.save(user);
        return mapToStudentResponse(user);
    }

    @Override
    public TeacherResponse addTeacher(TeacherRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUserName(request.getName());
        user.setUserLastname(request.getSurname());
        user.setCreated(LocalDate.now());
        user.setStatus(Status.ACTIVE);
        Course course = courseRepository.findById(request.getCourseId()).get();
        user.setCourse(course);
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("TEACHER"));
        user.setRoles(roles);
        userRepository.save(user);
        return mapToTeacherResponse(user);
    }

    @Override
    public StudentResponse updateStudent(Long id, StudentRequest request) {
        User user = userRepository.findById(id).get();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUserName(request.getName());
        user.setUserLastname(request.getSurname());
        user.setStudyFormat(StudyFormat.valueOf(request.getStudyFormat()));
        user.setGroup(groupRepository.findById(request.getGroupId()).get());
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName(request.getRoleName()));
        user.setRoles(roles);
        userRepository.save(user);
        return mapToStudentResponse(user);
    }

    @Override
    public TeacherResponse updateTeacher(Long id, TeacherRequest request) {
        User user = userRepository.findById(id).get();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUserName(request.getName());
        user.setUserLastname(request.getSurname());
        Role role = roleRepository.findByName(request.getRoleName());
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        if (role == null) {
            roles.add(roleRepository.findByName("STUDENT"));
        }
        Course course = courseRepository.findById(request.getCourseId()).get();
        user.setCourse(course);
        userRepository.save(user);
        return mapToTeacherResponse(user);
    }

    @Override
    public List<StudentResponse> getStudentByGroupId(Long groupId) {
        List<User> users = userRepository.getStudentsByGroupId(groupId);
        List<StudentResponse> responses = new ArrayList<>();
        for (User user : users) {
            responses.add(mapToStudentResponse(user));
        }
        return responses;
    }

    @Override
    public int sizeOfCompaniesStudents(Long companyId) {
        List<User> users = userRepository.getStudentsByCompany(companyId);
        return users.size();
    }


    private StudentResponse mapToStudentResponse(User user) {
        if (user == null) {
            return null;
        }
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setName(user.getUsername());
        studentResponse.setSurname(user.getUserLastname());
        studentResponse.setEmail(user.getEmail());
        studentResponse.setStudyFormat(user.getStudyFormat());
        studentResponse.setGroup(user.getGroup());
        studentResponse.setStatus(user.getStatus());
        studentResponse.setCreated(LocalDate.now());
        return studentResponse;
    }

    private TeacherResponse mapToTeacherResponse(User user) {
        if (user == null) {
            return null;
        }
        TeacherResponse teacherResponse = new TeacherResponse();
        teacherResponse.setFirstName(user.getUsername());
        teacherResponse.setLastname(user.getUserLastname());
        teacherResponse.setEmail(user.getEmail());
        teacherResponse.setCourse(user.getCourse());
        teacherResponse.setRoles(user.getRoles());
        return teacherResponse;
    }
}