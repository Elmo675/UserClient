package emil.burdach.userclient.service;

import emil.burdach.userclient.model.dto.UserDTO;
import emil.burdach.userclient.model.response.RandomUserResponse;
import emil.burdach.userclient.service.client.RandomUserClient;
import emil.burdach.userclient.service.mapper.RandomUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserClient {

    private final RandomUserClient randomUserClient;

    public List<UserDTO> users() {
        RandomUserResponse randomUserResponse = randomUserClient.getRandomUsersResponse();
        return RandomUserMapper.INSTANCE.mapToUserDTO(randomUserResponse.getResults());
    }
}
