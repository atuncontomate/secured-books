package es.mastercloudapps.books.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserBaseDto {

    @NotEmpty(message = "Nickname is mandatory")
    private String nickname;

    @NotEmpty(message = "Email is mandatory")
    private String email;
}
