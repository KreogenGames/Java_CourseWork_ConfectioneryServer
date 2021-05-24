package ConfectioneryApplicationServer.output;

import com.sun.istack.NotNull;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Setter
@ToString
public class ItemResponse {
    @NotNull
    private Long itemId;
    @NotBlank
    private String nameOfItem;
    @NotBlank
    private int priceOfItem;
    @NotBlank
    private int numOfItem;
}