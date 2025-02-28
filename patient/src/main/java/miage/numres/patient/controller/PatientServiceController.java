package miage.numres.patient.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import miage.numres.patient.model.Patient;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@Api(value = "Gestion des patients", description = "Opérations pour gérer les patients") // Annotation Swagger
public class PatientServiceController {

    // Liste statique des patients (simulation d'une base de données)
    private static final List<Patient> patients = Arrays.asList(
            new Patient(1, "Alice"),
            new Patient(2, "Bob"),
            new Patient(3, "Charlie")
    );

    // GET - Récupérer un patient par ID
    @RequestMapping(value = "/findPatientDetails/{patientId}", method = RequestMethod.GET)
    @ApiOperation(value = "Récupérer les détails d'un patient via son ID", response = Patient.class)
    public Patient getPatientDetails(@PathVariable int patientId) {
        System.out.println("Getting Patient details for " + patientId);
        return patients.stream()
                .filter(p -> p.getId() == patientId)
                .findFirst()
                .orElse(null);
    }

    // GET - Récupérer tous les patients
    @RequestMapping(value = "/patients", method = RequestMethod.GET)
    @ApiOperation(value = "Récupérer la liste de tous les patients", response = List.class)
    public List<Patient> getPatients() {
        System.out.println("Getting all patients");
        return patients;
    }
}
