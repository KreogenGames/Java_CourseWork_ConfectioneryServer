package ConfectioneryApplicationServer.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ConfectioneryApplicationServer.output.Outputer;
import ConfectioneryApplicationServer.services.ShopCartService;

import java.util.Map;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {
    private final ShopCartService shopCartService;
    private final Outputer outputer;

    @GetMapping
    public String index(Map<String, Object> model) {
        model.put(
                "shopCarts",
                shopCartService.takeAllShopCarts(outputer::toShopCartResponseList)
        );
        return "home";
    }
}