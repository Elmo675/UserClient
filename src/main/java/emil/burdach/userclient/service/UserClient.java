package emil.burdach.userclient.service;

import emil.burdach.userclient.model.dto.UserDTO;
import emil.burdach.userclient.model.response.RandomUserResponse;
import emil.burdach.userclient.service.client.RandomUserClientService;
import emil.burdach.userclient.service.mapper.RandomUserMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserClient {

    private final Logger log = LoggerFactory.getLogger(UserClient.class);
    private final RandomUserClientService randomUserClientService;

    public List<UserDTO> users() {
        log.info("Request to get List of users DTO");
        RandomUserResponse randomUserResponse = randomUserClientService.getRandomUsersResponse();
        return RandomUserMapper.INSTANCE.mapToUserDTO(randomUserResponse.getResults());
    }
}
