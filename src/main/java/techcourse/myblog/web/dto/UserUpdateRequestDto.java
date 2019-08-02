package techcourse.myblog.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserUpdateRequestDto {
    @NotBlank
    @Pattern(regexp = UserRequestDto.USERNAME_PATTERN, message = UserRequestDto.MESSAGE_USERNAME_FORMAT)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
