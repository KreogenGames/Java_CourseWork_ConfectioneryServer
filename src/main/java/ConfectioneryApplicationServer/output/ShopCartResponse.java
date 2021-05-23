package ConfectioneryApplicationServer.output;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Setter
@Getter
@ToString
public class ShopCartResponse { //Мб стоит, что-то еще добавить или переоформить
    @NotNull
    private Long id;
    private List<ItemResponse> items;
}