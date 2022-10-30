package emil.burdach.userclient.model.response;

import emil.burdach.userclient.model.dto.RandomUserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RandomUserResponse {
    @Builder.Default
    List<RandomUserDTO> results = new ArrayList<>();
}
