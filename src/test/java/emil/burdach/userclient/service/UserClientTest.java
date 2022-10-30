package emil.burdach.userclient.service;

import emil.burdach.userclient.model.dto.UserDTO;
import emil.burdach.userclient.service.client.RandomUserClientService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserClientTest {

    UserClient userClient;

    @BeforeAll
    void setUp() {
        userClient = new UserClient(new RandomUserClientService());
    }

    @Test
    void whenUsers_ThenTheyArePrintedProperlyAndListIsNotEmpty() {
        //given
        //when
        List<UserDTO> users = userClient.users();
        //then
        assertThat(users.isEmpty()).isFalse();
        users.forEach(System.out::println);
    }

    @Test
    void whenUsers_ThenNoFieldIsNullOrBlank() {
        //given
        //when
        List<UserDTO> users = userClient.users();
        //then
        users.forEach(user->{
            assertThat(user.getUuid()).isNotBlank();
            assertThat(user.getFirstName()).isNotBlank();
            assertThat(user.getLastName()).isNotBlank();
            assertThat(user.getCity()).isNotBlank();
            assertThat(user.getGender()).isNotNull();
            assertThat(user.getRegistrationDate()).isNotNull();
        });
    }
}