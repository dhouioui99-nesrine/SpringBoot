package com.example.IntegrationAPI.Base3.Service;


import com.example.IntegrationAPI.Base3.Repository.ProjetRepository;
import com.example.IntegrationAPI.Base3.model.Empl;
import com.example.IntegrationAPI.Base3.model.Projet;
import com.example.IntegrationAPI.MySql.Repository.ProjectRepository;
import com.example.IntegrationAPI.MySql.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjetRepository projetRepository;



    public void migrerEmployees() {
        List<Project> employees = projectRepository.findAll();

        for (Project emp : employees) {
            Projet newEmp = new Projet();

            newEmp.setName(emp.getName());
            newEmp.setDescription(emp.getDescription());
            newEmp.setStatus(emp.getStatus());


            projetRepository.save(newEmp);
        }
    }

    public List<Projet> getAllEmployees() {

        return  projetRepository.findAll();
    }

    // create
    public Projet save(Projet empl) {
        return projetRepository.save(empl);
    }
    //delete  par id
    public void deleteById(Long id) {
        Optional<Projet> empl = projetRepository.findById(id);
        if (empl.isEmpty()) {
            throw new RuntimeException("No employee found with empCode: " + id);
        }
        // Ici on sait que l'objet existe
        projetRepository.delete(empl.get());
    }


    // update  par id
    public Projet updateById(Long id, Projet updatedData) {
        Projet empl = projetRepository.findFirstById(id)
                .orElseThrow(() -> new RuntimeException("employe not found with empCode: " + id));

        empl.setName(updatedData.getName());
        empl.setDescription(updatedData.getDescription());
        empl.setStatus(updatedData.getStatus());


        return projetRepository.save(empl);
    }
    // afficher les projets par id
    public Projet getEmployeeById(Long id) {
        return projetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projet not found with id: " + id));
    }


}

