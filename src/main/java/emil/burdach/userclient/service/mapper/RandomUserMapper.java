package emil.burdach.userclient.service.mapper;

import emil.burdach.userclient.model.dto.RandomUserDTO;
import emil.burdach.userclient.model.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RandomUserMapper {

    RandomUserMapper INSTANCE = Mappers.getMapper(RandomUserMapper.class);

    UserDTO mapToUserDTO(RandomUserDTO randomUserDTO);

    List<UserDTO> mapToUserDTO(List<RandomUserDTO> randomUserDTOS);
}
