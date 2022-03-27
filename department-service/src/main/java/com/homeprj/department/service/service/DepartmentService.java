package com.homeprj.department.service.service;

import com.homeprj.department.service.entity.Department;
import com.homeprj.department.service.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;


    public Department saveDepartment(Department department) {
        log.info("inside saveDepartment of DepartmentService: ");
        return departmentRepository.save(department);
    }

    public Department findDepartmentById(Long departmentId){
        log.info("inside findDepartmentById of DepartmentService: ");
        return departmentRepository.findByDepartmentId(departmentId);
    }

}
