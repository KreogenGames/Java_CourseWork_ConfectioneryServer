package ConfectioneryApplicationServer.controllers;

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

import javax.validation.Valid;

@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {
    private final ShopCartService shopCartService;
    private final ItemService itemService;

    int messageAllRight = 1; //Ошибок не произошло
    int messageUnsuccessfullDel = 2;//Ошибка при удалении из БД
    int messageUnsuccessfullAdd = 3;//Ошибка при добавлении в БД

    @PostMapping("add")
    public int add(
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
            return messageUnsuccessfullAdd;
        }
        return messageAllRight;
    }

    @PostMapping("{itemId}/delete")
    public int delete(@PathVariable long itemId) {
        try {
            itemService.delete(itemId);
        } catch (Exception e) {
            e.printStackTrace();
            return messageUnsuccessfullDel;
        }
        return messageAllRight;
    }
}