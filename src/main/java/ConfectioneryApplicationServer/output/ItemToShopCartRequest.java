package ConfectioneryApplicationServer.output;

import ConfectioneryApplicationServer.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@ToString
@AllArgsConstructor
public class ItemToShopCartRequest {
    @NotBlank
    private final String nameOfItem;
    @NotNull
    private final int priceOfItem;
    @NotNull
    private final int numOfItem;
    @NotNull
    private final Long shopCartId;
}
