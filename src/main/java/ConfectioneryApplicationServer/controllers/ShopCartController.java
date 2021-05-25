package ConfectioneryApplicationServer.controllers;

import ConfectioneryApplicationServer.models.ResponseCodes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ConfectioneryApplicationServer.output.Outputer;
import ConfectioneryApplicationServer.services.ShopCartService;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@Controller
@RequestMapping("/shopCart")
@RequiredArgsConstructor
public class ShopCartController {
    private final Outputer outputer;
    private final ShopCartService shopCartService;

    @GetMapping
    public String getShopCarts(Map<String, Object> model) {
        model.put(
                "shopCarts",
                shopCartService.takeAllShopCarts(outputer::toShopCartResponseList)
        );
        return "shopCart";
    }

    @PostMapping("{shopCartId}")
    public String getShopCart(
            @PathVariable long shopCartId,
            Map<String, Object> model
    ) {
        model.put(
                "shopCart",
                shopCartService.takeShopCartById(shopCartId, outputer::toShopCartResponse)
        );
        return "shopCart";
    }

    @PostMapping("{shopCartId}/delete")
    public RedirectView delete(@PathVariable long shopCartId) {
        shopCartService.delete(shopCartId);
        return new RedirectView("/home");
    }

    @GetMapping("add")
    public String add() {
        return "add";
    }

    /*
    @GetMapping
    public String getShopcarts(Map<String, Object> model) {
        try {
            model.put(
                    "shopCarts",
                    shopCartService.takeAllShopCarts(outputer::toShopCartResponseList)
            );
        } catch (Exception e) {
            e.printStackTrace();
            return String.valueOf(ResponseCodes.getMessageUnsuccessfullShopCartGet);
        }
        return String.valueOf(ResponseCodes.messageAllRight);
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
            return String.valueOf(ResponseCodes.getMessageUnsuccessfullShopCartIdGet);
        }
        return String.valueOf(ResponseCodes.messageAllRight);
    }*/
}