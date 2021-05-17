package ConfectioneryApplicationServer.output;

import com.sun.istack.NotNull;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Setter
@ToString
public class ShopCartResponse {
    @NotNull
    private Long id;
    private List<ItemResponse> items;
}