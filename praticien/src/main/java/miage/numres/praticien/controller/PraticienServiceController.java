package miage.numres.praticien.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import miage.numres.praticien.model.Praticien;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@Api(value = "Gestion des praticiens", description = "Opérations pour gérer les praticiens") // Annotation Swagger
public class PraticienServiceController {

    // Liste statique des praticiens (simulation d'une base de données)
    private static final List<Praticien> praticiens = Arrays.asList(
            new Praticien(1, "Dr. Smith"),
            new Praticien(2, "Dr. Johnson"),
            new Praticien(3, "Dr. Williams")
    );

    // GET - Récupérer un praticien par ID
    @RequestMapping(value = "/findPraticienDetails/{praticienId}", method = RequestMethod.GET)
    @ApiOperation(value = "Récupérer les détails d'un praticien via son ID", response = Praticien.class)
    public Praticien getPraticienDetails(@PathVariable int praticienId) {
        System.out.println("Getting Praticien details for " + praticienId);
        return praticiens.stream()
                .filter(p -> p.getId() == praticienId)
                .findFirst()
                .orElse(null);
    }

    // GET - Récupérer tous les praticiens
    @RequestMapping(value = "/praticiens", method = RequestMethod.GET)
    @ApiOperation(value = "Récupérer la liste de tous les praticiens", response = List.class)
    public List<Praticien> getPraticiens() {
        System.out.println("Getting all praticiens");
        return praticiens;
    }
}
