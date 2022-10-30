package emil.burdach.userclient.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import emil.burdach.userclient.model.Location;
import emil.burdach.userclient.model.Login;
import emil.burdach.userclient.model.Name;
import emil.burdach.userclient.model.Registration;
import emil.burdach.userclient.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RandomUserDTO {
    Gender gender;
    Name name;
    Location location;
    Login login;
    Registration registered;
}
