package emil.burdach.userclient.model.response;

import emil.burdach.userclient.model.dto.RandomUserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RandomUserResponse {
    List<RandomUserDTO> results;

}
