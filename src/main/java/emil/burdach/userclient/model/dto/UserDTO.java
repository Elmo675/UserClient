package emil.burdach.userclient.model.dto;

import emil.burdach.userclient.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    String uuid;
    String firstName;
    String lastName;
    String city;
    Gender gender;
    ZonedDateTime registrationDate;
}
