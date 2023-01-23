package com.example.demo.service;

import com.example.demo.dto.ClassroomsDto;
import com.example.demo.mappers.ClassroomsMapper;
import com.example.demo.models.Classrooms;
import com.example.demo.repositoryes.ClassroomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomsService implements com.example.demo.service.interfaces.ClassroomsService {
    final private ClassroomsRepository classroomsRepository;

    @Autowired
    public ClassroomsService(ClassroomsRepository classroomsRepository) {
        this.classroomsRepository = classroomsRepository;
    }

    @Cacheable(cacheNames = "classrooms", key = "#id", condition="#p0!=null")
    public List<Classrooms> getClassrooms() {return classroomsRepository.findAll();}
/*    public ClassroomsDto getClassroomsDto(Long id){
        Classrooms classrooms=getClassroomById(id);
        return ClassroomToDto(classrooms);
    }

    public ClassroomsDto ClassroomToDto (Classrooms classrooms){
        return classroomsMapper.mapToDto(classrooms);
    }*/
    @Cacheable(cacheNames = "classrooms", key = "#id", condition="#p0!=null")
    public Classrooms getClassroomById(Long id){
        return classroomsRepository.findById(id).get();
    }

    @CachePut(cacheNames = "classrooms", key="#p0", condition="#p0!=null")
    public void addNewClassroom(Classrooms classrooms){
        Optional<Classrooms> classroomsOptional = classroomsRepository.findByName(classrooms.getName());
        if (classroomsOptional.isPresent()) {
            throw new IllegalStateException("Already existing classroom");
        }
        classroomsRepository.save(classrooms);
    }

    @CacheEvict(cacheNames = "classrooms",key="#p0", condition="#p0!=null")
    public void deleteClassroom(Long classroomId){
        boolean exists = classroomsRepository.existsById(classroomId);
        if(!exists){
            throw new IllegalStateException("Classroom identified by " + classroomId+" is not existing");
            }
        classroomsRepository.deleteById(classroomId);
    }

}
