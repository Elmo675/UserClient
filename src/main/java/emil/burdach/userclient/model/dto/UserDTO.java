package emil.burdach.userclient.model.dto;

import emil.burdach.userclient.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    String uuid;
    String firstName;
    String lastName;
    String City;
    Gender gender;
    Instant registrationDate;
}
