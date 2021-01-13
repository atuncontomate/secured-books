package es.mastercloudapps.books.service;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@Data
public class UserSession {

    private String user;
}
