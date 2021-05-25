package ConfectioneryApplicationServer.output;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class SignUpRequest {
    @NotNull
    @NotEmpty
    private final String username;

    /*@NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String firstName;

    private String middleName;*/

    @NotNull
    @NotEmpty
    private final String password;
    private final String matchingPassword;
}