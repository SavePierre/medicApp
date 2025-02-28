package miage.numres.api_gateway.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
@Api(value = "API Gateway", description = "API Gateway pour gérer les patients et praticiens")
public class PPController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String PATIENT_SERVICE_URL = "http://patient";
    private static final String PRATICIEN_SERVICE_URL = "http://praticien";

    @RequestMapping("/patients/{id}")
    @ApiOperation(value = "Récupère un patient par ID", response = String.class)
    @HystrixCommand(fallbackMethod = "fallbackGetPatient")
    public String getPatient(@PathVariable int id) {
        ResponseEntity<String> response = restTemplate.exchange(
                PATIENT_SERVICE_URL + "/findPatientDetails/" + id,
                HttpMethod.GET, null, String.class
        );
        return response.getBody() != null ? response.getBody() : "Patient not found";
    }

    public String fallbackGetPatient(int id) {
        return "Fallback: Patient service unavailable or patient not found for ID " + id;
    }

    @RequestMapping("/praticiens/{id}")
    @ApiOperation(value = "Récupère un praticien par ID", response = String.class)
    @HystrixCommand(fallbackMethod = "fallbackGetPraticien")
    public String getPraticien(@PathVariable int id) {
        ResponseEntity<String> response = restTemplate.exchange(
                PRATICIEN_SERVICE_URL + "/findPraticienDetails/" + id,
                HttpMethod.GET, null, String.class
        );
        return response.getBody() != null ? response.getBody() : "Praticien not found";
    }

    public String fallbackGetPraticien(int id) {
        return "Fallback: Praticien service unavailable or praticien not found for ID " + id;
    }

    @RequestMapping("/patients")
    @ApiOperation(value = "Récupère la liste complète des patients", response = String.class)
    @HystrixCommand(fallbackMethod = "fallbackGetAllPatients")
    public String getAllPatients() {
        ResponseEntity<String> response = restTemplate.exchange(
                PATIENT_SERVICE_URL + "/patients",
                HttpMethod.GET, null, String.class
        );
        return response.getBody();
    }

    public String fallbackGetAllPatients() {
        return "Fallback: Unable to retrieve patient data at the moment.";
    }

    @RequestMapping("/praticiens")
    @ApiOperation(value = "Récupère la liste complète des praticiens", response = String.class)
    @HystrixCommand(fallbackMethod = "fallbackGetAllPraticiens")
    public String getAllPraticiens() {
        ResponseEntity<String> response = restTemplate.exchange(
                PRATICIEN_SERVICE_URL + "/praticiens",
                HttpMethod.GET, null, String.class
        );
        return response.getBody();
    }

    public String fallbackGetAllPraticiens() {
        return "Fallback: Unable to retrieve praticien data at the moment.";
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
