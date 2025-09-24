package com.example.IntegrationAPI.Base3.Service;

import com.example.IntegrationAPI.Base3.Repository.DepRepo;
import com.example.IntegrationAPI.Base3.model.Dep;
import com.example.IntegrationAPI.Base3.model.Empl;
import com.example.IntegrationAPI.Postgres.model.Departement;
import com.example.IntegrationAPI.Postgres.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepService {
    @Autowired
    private DepartementRepository departementRepository;

    @Autowired
    private DepRepo depRepository;

    public void migrerDepartements() {
        List<Departement> departements = departementRepository.findAll();
        List<Dep> deps = departements.stream().map(dep -> {
            Dep d = new Dep();
            d.setCodeDept(dep.getDept_code());
            d.setName(dep.getDept_name());
            d.setIs_default(dep.isIs_default());
            return d;
        }).collect(Collectors.toList());

        depRepository.saveAll(deps);
    }

    public List<Dep> getAllEmployees() {

        return  depRepository.findAll();


    }
}


