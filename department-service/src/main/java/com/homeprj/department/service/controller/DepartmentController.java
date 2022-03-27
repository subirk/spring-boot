package com.homeprj.department.service.controller;

import com.homeprj.department.service.entity.Department;
import com.homeprj.department.service.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/")
    public Department saveDepartment(@RequestBody Department department){
        log.info("inside saveDepartment of DepartmentController:");
        return departmentService.saveDepartment(department);
    }

    @RequestMapping("/{id}")
    public Department findDepartmentById(@PathVariable("id") Long departmentId){
        log.info("inside findDepartmentById of DepartmentService: ");
        return departmentService.findDepartmentById(departmentId);
    }

}
