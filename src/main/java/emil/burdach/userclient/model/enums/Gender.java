package emil.burdach.userclient.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Gender {
    FEMALE("female"),
    MALE("male");

    private final String gender;

    @JsonCreator
    public static Gender fromString(String gender) {
        return gender == null
                ? null
                : Gender.valueOf(gender.toUpperCase());
    }

    @JsonValue
    public String getGender() {
        return gender;
    }
}
