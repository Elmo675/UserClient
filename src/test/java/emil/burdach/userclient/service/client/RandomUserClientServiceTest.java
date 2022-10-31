package emil.burdach.userclient.service.client;

import emil.burdach.userclient.fixtures.RandomUserResponseFixtures;
import emil.burdach.userclient.model.dto.RandomUserDTO;
import emil.burdach.userclient.model.response.RandomUserResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RandomUserClientServiceTest {

    private final static String URL = "https://randomuser.me/api/";

    private RandomUserClientService randomUserClientService;
    private RestTemplate restTemplate;

    @BeforeAll
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        randomUserClientService = new RandomUserClientService(restTemplate);
    }

    @Test
    void givenStatusOkAndReturnedBodyFemale_WhenGetRandomUsersResponse_ThenFirstNameEqualAndLoginUuidEqual() {
        //given
        when(restTemplate.exchange(eq(URL), eq(HttpMethod.GET), any(), eq(RandomUserResponse.class)))
                .thenReturn(new ResponseEntity<>(RandomUserResponseFixtures.getFemaleRandomUserResponse(), HttpStatus.OK));
        //when
        RandomUserResponse randomUsersResponse = randomUserClientService.getRandomUsersResponse();
        //then
        RandomUserDTO responseUserDTO = randomUsersResponse.getResults().stream().findFirst().orElseThrow();
        assertThat(responseUserDTO.getName().getFirst()).isEqualTo("FirstName");
        assertThat(responseUserDTO.getLogin().getUuid()).isEqualTo("UUID");
    }

    @Test
    void givenStatusNotFound_WhenGetRandomUsersResponse_ThenHttpClientErrorExceptionThrown() {
        //given
        when(restTemplate.exchange(eq(URL), eq(HttpMethod.GET), any(), eq(RandomUserResponse.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));
        //when
        //then
        assertThatThrownBy(() -> randomUserClientService.getRandomUsersResponse())
                .isExactlyInstanceOf(HttpClientErrorException.class);
    }

    @Test
    void givenStatusInternalServerError_WhenGetRandomUsersResponse_ThenHttpServerErrorExceptionThrown() {
        //given
        when(restTemplate.exchange(eq(URL), eq(HttpMethod.GET), any(), eq(RandomUserResponse.class)))
                .thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
        //when
        //then
        assertThatThrownBy(() -> randomUserClientService.getRandomUsersResponse())
                .isExactlyInstanceOf(HttpServerErrorException.class);
    }
}