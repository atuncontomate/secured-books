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
public class BookBaseDto {

    @NotEmpty(message = "Title is mandatory")
    private String title;

    private String author;

    private String publisher;

    private Integer year;

    private String summary;
}
