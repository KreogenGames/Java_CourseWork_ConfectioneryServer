package ConfectioneryApplicationServer.controllers;

import ConfectioneryApplicationServer.models.ResponseCodes;
import ConfectioneryApplicationServer.output.RegisterRequest;
import ConfectioneryApplicationServer.output.UserAlreadyExistsException;
import ConfectioneryApplicationServer.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/sign-up")
@RequiredArgsConstructor
public class SignUpController {
    private final UserService userService;

    @GetMapping
    public String index() {
        return "sign_up";
    }

    @PostMapping
    public String addUser(
            @Valid @ModelAttribute("signUpUser") RegisterRequest registerRequest,
            BindingResult result,
            Map<String, Object> model
    ) {
        if (!result.hasErrors()) {
            if (!registerRequest.getPassword().equals(registerRequest.getMatchingPassword())) {
                model.put("notMatched", true);
                return "sign_up";
                //return String.valueOf(ResponseCodes.messageHasNotMatched);
            }
            try {
                userService.addNewUser(registerRequest);
                return "redirect:/login";
            } catch (UserAlreadyExistsException exp) {
                model.put("alreadyExists", true);
                return "sign_up";
                //return String.valueOf(ResponseCodes.messageAlreadyExists);
            }
        }
        return "sign_up";
        //return String.valueOf(ResponseCodes.messageAllRight);
    }
}