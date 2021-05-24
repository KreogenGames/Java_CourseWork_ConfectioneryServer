package ConfectioneryApplicationServer.controllers;

import ConfectioneryApplicationServer.models.ResponseCodes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ConfectioneryApplicationServer.output.ItemToShopCartRequest;
import ConfectioneryApplicationServer.services.ShopCartService;
import ConfectioneryApplicationServer.services.ItemService;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {
    private final ShopCartService shopCartService;
    private final ItemService itemService;

    @PostMapping("add")
    public RedirectView add(
            @Valid @ModelAttribute("addItem") ItemToShopCartRequest itemToShopCartRequest,
            BindingResult result
    ) {
        try {
            if (!result.hasErrors()) {
                shopCartService.publish(itemToShopCartRequest);
                itemService.publish(itemToShopCartRequest);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //return String.valueOf(ResponseCodes.messageUnsuccessfullAdd);
        }
        return new RedirectView("/home");
        //return String.valueOf(ResponseCodes.messageAllRight);
    }

    @PostMapping("{shopCartId}/delete")
    public RedirectView delete(@PathVariable long shopCartId) {
        try {
            itemService.delete(shopCartId);
        } catch (Exception e) {
            e.printStackTrace();
            //return String.valueOf(ResponseCodes.messageUnsuccessfullDel);
        }
        return new RedirectView("/home");
        //String.valueOf(ResponseCodes.messageAllRight);
    }
}