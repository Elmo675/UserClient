package emil.burdach.userclient.service.client;

import emil.burdach.userclient.exception.RandomUserClientServiceException;
import emil.burdach.userclient.model.response.RandomUserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class RandomUserClientService {

    private final RestTemplate restTemplate;

    private final Logger log = LoggerFactory.getLogger(RandomUserClientService.class);
    private final String URL;

    public RandomUserClientService() {
        restTemplate = new RestTemplateBuilder().build();
        URL = "https://randomuser.me/api/";
    }

    public RandomUserClientService(String URL) {
        restTemplate = new RestTemplateBuilder().build();
        this.URL = URL;
    }

    public RandomUserResponse getRandomUsersResponse() {
        log.info("Request to get RandomUsers");
        try {
            HttpHeaders headers = createHeaders();
            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<RandomUserResponse> response = restTemplate.exchange(URL, HttpMethod.GET, request, RandomUserResponse.class);
            return Optional.ofNullable(response.getBody())
                    .orElseThrow(() -> new RandomUserClientServiceException("RandomUsersApi response body is null"));
        } catch (HttpServerErrorException | HttpClientErrorException e) {
            log.error("Request failed. Exception status: {}, body: {}", e.getStatusCode(), e.getResponseBodyAsString());
            throw new RandomUserClientServiceException(e.getStatusCode(), e.getResponseBodyAsString());
        } catch (Exception e) {
            log.error("Request failed. Exception Message: {}", e.getMessage());
            throw new RandomUserClientServiceException(e.getMessage());
        }
    }

    private HttpHeaders createHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
