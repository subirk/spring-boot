package com.homeprj.user.service.service;

import com.homeprj.user.service.entity.User;
import com.homeprj.user.service.repository.UserRepository;
import com.homeprj.user.service.vo.Department;
import com.homeprj.user.service.vo.ResponseTemplateVO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String USER_DEPARTMENT =  "userDEPT";

    public User saveUser(User user) {
        log.info("inside saveUser method os UserService");
        return userRepository.save(user);
    }

//    @Retry(name = USER_DEPARTMENT)
//    @CircuitBreaker(name = USER_DEPARTMENT, fallbackMethod = "getUserWithDepartmentFallback")
    @RateLimiter(name = USER_DEPARTMENT)
    public ResponseTemplateVO getUserWithDepartment(Long userId) {
//        int count=1;
        ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);
//        log.info("Retry method called "+count++ + " times at "+ new Date());
        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/"
                + user.getDepartmentId(),Department.class);
        responseTemplateVO.setUser(user);
        responseTemplateVO.setDepartment(department);
        return responseTemplateVO;
    }

    public ResponseTemplateVO getUserWithDepartmentFallback(Exception e){
        ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();
        Department department = new Department();
        department.setDepartmentName("This is Fallback");
        responseTemplateVO.setDepartment(department);
        return responseTemplateVO;
    }

}
