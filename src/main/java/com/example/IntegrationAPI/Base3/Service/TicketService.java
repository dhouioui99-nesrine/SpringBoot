package com.example.IntegrationAPI.Base3.Service;

import com.example.IntegrationAPI.Base3.Repository.TicketRepository;
import com.example.IntegrationAPI.Base3.Repository.issue_statRepository;
import com.example.IntegrationAPI.Base3.model.Ticket;
import com.example.IntegrationAPI.Base3.model.issue_stat;
import com.example.IntegrationAPI.MySql.Repository.Issue_statusesRepository;
import com.example.IntegrationAPI.MySql.Repository.TrackerRepository;
import com.example.IntegrationAPI.MySql.entity.Tracker;
import com.example.IntegrationAPI.MySql.entity.issue_statuses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TicketService {
    @Autowired
    private TrackerRepository trackerRepository;

    @Autowired
    private TicketRepository ticketRepository;



    public void migrerEmployees() {
        List<Tracker> employees = trackerRepository.findAll();

        for (Tracker emp : employees) {
            Ticket newEmp = new Ticket();

            newEmp.setName(emp.getName());
            newEmp.setDescription(emp.getDescription());



            ticketRepository.save(newEmp);
        }
    }
}


