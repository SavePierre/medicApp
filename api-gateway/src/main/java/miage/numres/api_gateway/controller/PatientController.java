package miage.numres.api_gateway.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PatientController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/patientDetails/{patientId}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "fallback")
    public String getPatientDetails(@PathVariable int patientId) {
        System.out.println("Getting Patient details for " + patientId);

        String response = this.restTemplate.exchange("http://patient-service/findPatientDetails/{patientId}",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                }, patientId).getBody();

        System.out.println("Response Body " + response);

        return "Patient Id -  " + patientId + " [ Patient Details " + response + " ]";
    }

    public String fallback(int patientId) {
        return "Fallback response:: No patient details available temporarily for Patient Id - " + patientId;
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
