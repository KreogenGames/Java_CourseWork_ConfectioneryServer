package ConfectioneryApplicationServer.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ConfectioneryApplicationServer.output.Outputer;
import ConfectioneryApplicationServer.services.ShopCartService;

import java.util.Map;

@Controller
@RequestMapping("/shopCart")
@RequiredArgsConstructor
public class ShopCartController {
    private final Outputer outputer;
    private final ShopCartService shopCartService;

    @GetMapping
    public String getShopcarts(Map<String, Object> model) {
        try {
            model.put(
                    "shopCarts",
                    shopCartService.takeAllShopCarts(outputer::toShopCartResponseList)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "shopCart";
    }

    @PostMapping("{shopCartId}")
    public String getShopCart(
            @PathVariable long shopCartId,
            Map<String, Object> model
    ) {
        try {
            model.put(
                    "shopCart",
                    shopCartService.takeShopCartById(shopCartId, outputer::toShopCartResponse)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "shopCart";
    }

    @GetMapping("{shopCartId}/delete")
    public void getAllShopCarts() { //Мб придумаю где его использовать
        shopCartService.getAllShopCarts();
    }
}