package es.mastercloudapps.books.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CommentBaseDto {

    @NotEmpty(message = "User is mandatory")
    private String user;

    @Min(value = 0, message = "Score must be equals or greater than 0")
    @Max(value = 5, message = "Score must be equals or less than 5")
    private int score;

    private String content;
}
