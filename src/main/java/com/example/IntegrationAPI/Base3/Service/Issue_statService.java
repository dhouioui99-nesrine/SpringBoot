package com.example.IntegrationAPI.Base3.Service;


import com.example.IntegrationAPI.Base3.Repository.issue_statRepository;

import com.example.IntegrationAPI.Base3.model.issue_stat;
import com.example.IntegrationAPI.MySql.Repository.Issue_statusesRepository;

import com.example.IntegrationAPI.MySql.entity.issue_statuses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Issue_statService {

    @Autowired
    private Issue_statusesRepository issueStatusesRepository;

    @Autowired
    private issue_statRepository issueStatRepository;



    public void migrerEmployees() {
        List<issue_statuses> employees = issueStatusesRepository.findAll();

        for (issue_statuses emp : employees) {
            issue_stat newEmp = new issue_stat();

            newEmp.setName(emp.getName());



            issueStatRepository.save(newEmp);
        }
    }
}


