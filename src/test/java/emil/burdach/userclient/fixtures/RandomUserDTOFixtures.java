package emil.burdach.userclient.fixtures;

import emil.burdach.userclient.model.Location;
import emil.burdach.userclient.model.Login;
import emil.burdach.userclient.model.Name;
import emil.burdach.userclient.model.Registration;
import emil.burdach.userclient.model.dto.RandomUserDTO;
import emil.burdach.userclient.model.enums.Gender;

import java.time.ZonedDateTime;

public class RandomUserDTOFixtures {

    public static RandomUserDTO getFemale() {
        return RandomUserDTO.builder()
                .name(new Name("FirstName", "LastName"))
                .login(new Login("UUID"))
                .gender(Gender.FEMALE)
                .location(new Location("CITY"))
                .registered(new Registration(ZonedDateTime.now().plusHours(1)))
                .build();
    }
}
