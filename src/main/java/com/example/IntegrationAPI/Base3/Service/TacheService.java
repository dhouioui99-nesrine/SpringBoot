package com.example.IntegrationAPI.Base3.Service;

import com.example.IntegrationAPI.Base3.Repository.ProjetRepository;
import com.example.IntegrationAPI.Base3.Repository.TacheRepository;
import com.example.IntegrationAPI.Base3.Repository.TicketRepository;
import com.example.IntegrationAPI.Base3.model.Projet;
import com.example.IntegrationAPI.Base3.model.Tache;
import com.example.IntegrationAPI.Base3.model.Ticket;
import com.example.IntegrationAPI.MySql.Repository.IssuesRepository;
import com.example.IntegrationAPI.MySql.entity.Project;
import com.example.IntegrationAPI.MySql.entity.Tracker;

import com.example.IntegrationAPI.MySql.entity.issues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TacheService {
    @Autowired
    private IssuesRepository issuesRepository;

    @Autowired
    private TacheRepository tacheRepository;

    @Autowired
    private  ProjetRepository projetRepository;




    public void migrerEmployees() {
        List<issues> employees = issuesRepository.findAll();

        for (issues emp : employees) {
            Tache newEmp = new Tache();
        newEmp.setTracker(emp.getTrackerId().getName());
            // Créer un objet Projet à partir de Project
            if (emp.getProjectId() != null) {
                Project oldProject = emp.getProjectId();
                Projet projet = new Projet();
                projet.setId(oldProject.getId());        // si tu veux garder l’ID
                projet.setName(oldProject.getName());    // mapper le nom
                // mapper d’autres champs si nécessaire
                newEmp.setProjet(projet);
            }
            newEmp.setSubject(emp.getSubject());
            newEmp.setDescription(emp.getDescription());
            newEmp.setStatuts(emp.getStatut().getName());
            newEmp.setDue_date(emp.getDue_date());
            newEmp.setStart_date(emp.getStart_date());
            if (emp.getAssigned() != null) {
                String firstname = emp.getAssigned().getFirstname() != null ? emp.getAssigned().getFirstname() : "";
                String lastname = emp.getAssigned().getLastname() != null ? emp.getAssigned().getLastname() : "";
                String fullname = (firstname + " " + lastname).trim();

                newEmp.setAssigned(fullname.isEmpty() ? "Nom inconnu" : fullname);
            } else {
                newEmp.setAssigned("Non assigné");
            }


            tacheRepository.save(newEmp);
        }
    }
    public List<Tache> getAllEmployees() {

        return  tacheRepository.findAll();
    }

    // create
    public Tache save(Tache empl) {
        return tacheRepository.save(empl);
    }
    //delete  par id
    public void deleteById(Long id) {
        Optional<Tache> empl = tacheRepository.findById(id);
        if (empl.isEmpty()) {
            throw new RuntimeException("No employee found with empCode: " + id);
        }
        // Ici on sait que l'objet existe
        tacheRepository.delete(empl.get());
    }


    // update  par id
    public Tache updateById(Long id, Tache updatedData) {
        Tache empl = tacheRepository.findFirstById(id)
                .orElseThrow(() -> new RuntimeException("employe not found with empCode: " + id));

        empl.setTracker(updatedData.getTracker());
        empl.setProjet(updatedData.getProjet());
        empl.setDescription(updatedData.getDescription());
        empl.setStatuts(updatedData.getStatuts());
        empl.setSubject(updatedData.getSubject());
        empl.setDue_date(updatedData.getDue_date());
        empl.setStart_date(updatedData.getStart_date());
        empl.setAssigned(updatedData.getAssigned());

        return tacheRepository.save(empl);
    }
    // afficher l par id
    public Tache getEmployeeById(Long id) {
        return tacheRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tache not found with id: " + id));
    }


    public List<Tache> getTachesByProjetId(Long projetId) {
        return tacheRepository.findByProjetId(projetId);
    }


    // Ajouter une tâche à un projet
    public Tache ajouterTacheAuProjet(Long projetId, Tache tache) {
        Projet projet = projetRepository.findById(projetId)
                .orElseThrow(() -> new RuntimeException("Projet introuvable avec id: " + projetId));

        tache.setProjet(projet); // Associer projet à la tâche
        return tacheRepository.save(tache);
    }
}

