package emil.burdach.userclient.service.mapper;

import emil.burdach.userclient.model.dto.RandomUserDTO;
import emil.burdach.userclient.model.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RandomUserMapper {

    RandomUserMapper INSTANCE = Mappers.getMapper(RandomUserMapper.class);

    @Mapping(source = "name.first", target = "firstName")
    @Mapping(source = "name.last", target = "lastName")
    @Mapping(source = "login.uuid", target = "uuid")
    @Mapping(source = "location.city", target = "city")
    @Mapping(source = "registered.date", target = "registrationDate")
    UserDTO mapToUserDTO(RandomUserDTO randomUserDTO);

    List<UserDTO> mapToUserDTO(List<RandomUserDTO> randomUserDTOS);
}
