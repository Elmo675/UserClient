package emil.burdach.userclient.service.client;

import emil.burdach.userclient.model.response.RandomUserResponse;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class RandomUserClientService {

    private final RestTemplate restTemplate;

    private final Logger log = LoggerFactory.getLogger(RandomUserClientService.class);
    private final static String URL = "https://randomuser.me/api/";

    public RandomUserClientService() {
        restTemplate = new RestTemplateBuilder().build();
    }

    public RandomUserResponse getRandomUsersResponse() {
        log.info("Request to get RandomUsers");
        try {
            HttpHeaders headers = createHeaders();
            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<RandomUserResponse> response = restTemplate.exchange(URL, HttpMethod.GET, request, RandomUserResponse.class);
            return response.getBody();
        } catch (HttpServerErrorException | HttpClientErrorException e) {
            log.error("Request failed. Exception status: {}, body: {}", e.getStatusCode(), e.getResponseBodyAsString());
            throw e;
        } catch (Exception e) {
            log.error("Request failed. Exception Message: {}", e.getMessage());
            throw e;
        }
    }

    private HttpHeaders createHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
