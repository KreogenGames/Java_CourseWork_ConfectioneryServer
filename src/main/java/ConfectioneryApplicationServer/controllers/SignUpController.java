package ConfectioneryApplicationServer.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ConfectioneryApplicationServer.output.RegisterRequest;
import ConfectioneryApplicationServer.output.UserAlreadyExistsException;
import ConfectioneryApplicationServer.services.UserService;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/sign-up")
@RequiredArgsConstructor
public class SignUpController {
    private final UserService userService;

    /*@GetMapping
    public String index() {
        return "sign_up";
    }*/
    int messageAllRight = 1; //Ошибок не произошло
    int messageAlreadyExists = 2;//Данный пользователь уже имеется в БД
    int messageHasNotMatched = 3;//Пароль не повторен или его повторили неправильно

    @PostMapping
    public String addUser(
            @Valid @ModelAttribute("signUpUser") RegisterRequest registerRequest,
            BindingResult result,
            Map<String, Object> model
    ) {
        if (!result.hasErrors()) {
            if (!registerRequest.getPassword().equals(registerRequest.getMatchingPassword())) {
                model.put("notMatched", true);
                return String.valueOf(messageHasNotMatched);
            }
            try {
                userService.addNewUser(registerRequest);
            } catch (UserAlreadyExistsException exp) {
                model.put("alreadyExists", true);
                return String.valueOf(messageAlreadyExists);
            }
        }
        return String.valueOf(messageAllRight);
    }
}