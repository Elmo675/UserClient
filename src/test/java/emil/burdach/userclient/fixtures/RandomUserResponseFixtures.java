package emil.burdach.userclient.fixtures;

import emil.burdach.userclient.model.response.RandomUserResponse;

import java.util.ArrayList;
import java.util.List;

public class RandomUserResponseFixtures {
    public static RandomUserResponse getFemaleRandomUserResponse() {
        return new RandomUserResponse(new ArrayList<>(List.of(RandomUserDTOFixtures.getFemale())));
    }
}
