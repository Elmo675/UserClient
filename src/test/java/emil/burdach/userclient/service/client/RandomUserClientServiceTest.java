package emil.burdach.userclient.service.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.WireMockServer;
import emil.burdach.userclient.exception.RandomUserClientServiceException;
import emil.burdach.userclient.fixtures.RandomUserResponseFixtures;
import emil.burdach.userclient.model.dto.RandomUserDTO;
import emil.burdach.userclient.model.response.RandomUserResponse;
import emil.burdach.userclient.utils.ObjectMapperTestUtils;
import org.junit.jupiter.api.*;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RandomUserClientServiceTest {

    private final static String URL = "http://localhost:8080/api";

    private RandomUserClientService randomUserClientService;
    private WireMockServer wireMockServer;

    @BeforeAll
    void setUp() {
        randomUserClientService = new RandomUserClientService(URL);
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        configureFor("localhost", 8080);
    }

    @BeforeEach
    void prepareTest() {
        wireMockServer.resetAll();
    }

    @AfterAll
    void cleanUp() {
        wireMockServer.stop();
    }

    @Test
    void givenStatusOkAndReturnedBodyFemale_WhenGetRandomUsersResponse_ThenFirstNameEqualAndLoginUuidEqual() throws JsonProcessingException {
        //given
        String responseAsString = ObjectMapperTestUtils
                .getObjectMapper()
                .writeValueAsString(RandomUserResponseFixtures.getFemaleRandomUserResponse());
        stubFor(get(urlEqualTo("/api"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(responseAsString)
                        .withHeader("Content-Type", "application/json")));
        //when
        RandomUserResponse randomUsersResponse = randomUserClientService.getRandomUsersResponse();
        //then
        RandomUserDTO responseUserDTO = randomUsersResponse.getResults().stream().findFirst().orElseThrow(AssertionError::new);
        assertThat(responseUserDTO.getName().getFirst()).isEqualTo("FirstName");
        assertThat(responseUserDTO.getLogin().getUuid()).isEqualTo("UUID");
    }

    @Test
    void givenStatusOkAndReturnedBodyEmpty_WhenGetRandomUsersResponse_ThenRandomUserClientServiceExceptionIsThrownAndMessageEqual() {
        //given
        stubFor(get(urlEqualTo("/api"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("")
                        .withHeader("Content-Type", "application/json")));
        //when
        //then
        assertThatThrownBy(() -> randomUserClientService.getRandomUsersResponse())
                .isExactlyInstanceOf(RandomUserClientServiceException.class)
                .message().isEqualTo("RandomUsersApi response body is null");
    }

    @Test
    void givenStatusOkAndReturnedBodyBadJson_WhenGetRandomUsersResponse_ThenRandomUserClientServiceExceptionIsThrownAndMessageContains() {
        //given
        stubFor(get(urlEqualTo("/api"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("BAD_JSON")
                        .withHeader("Content-Type", "application/json")));
        //when
        //then
        assertThatThrownBy(() -> randomUserClientService.getRandomUsersResponse())
                .isExactlyInstanceOf(RandomUserClientServiceException.class)
                .message().contains("JSON parse error");
    }

    @Test
    void givenStatusNotFound_WhenGetRandomUsersResponse_ThenRandomUserClientServiceExceptionThrownAndMessageEqual() {
        //given
        stubFor(get(urlEqualTo("/api"))
                .willReturn(aResponse()
                        .withStatus(404)));
        //when
        //then
        assertThatThrownBy(() -> randomUserClientService.getRandomUsersResponse())
                .isExactlyInstanceOf(RandomUserClientServiceException.class)
                .message().isEqualTo("Exception status: 404, body: ");
    }

    @Test
    void givenStatusInternalServerError_WhenGetRandomUsersResponse_ThenRandomUserClientServiceExceptionThrownAndMessageEqual() {
        //given
        stubFor(get(urlEqualTo("/api"))
                .willReturn(aResponse()
                        .withStatus(500)));
        //when
        //then
        assertThatThrownBy(() -> randomUserClientService.getRandomUsersResponse())
                .isExactlyInstanceOf(RandomUserClientServiceException.class)
                .message().isEqualTo("Exception status: 500, body: ");
    }
}