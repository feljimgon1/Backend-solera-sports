package solerasports.server.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import solerasports.server.models.enums.UserType;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column
    @Size(max = 30)
    @NotNull
    @NotBlank
    private String email;

    @Column
    @Size(min = 8)
    @NotBlank
    @NotNull
    private String password;

    @Column
    @NotBlank
    @NotNull
    private UserType userType;

}
