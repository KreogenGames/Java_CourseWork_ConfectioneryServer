package ConfectioneryApplicationServer.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import ConfectioneryApplicationServer.models.Item;
import ConfectioneryApplicationServer.repositories.ShopCartRepository;
import ConfectioneryApplicationServer.repositories.ItemRepository;

import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ItemServiceTest {
    @Mock
    private ShopCartRepository shopCartRepository;
    @Mock
    private ItemRepository itemRepository;

    @Test
    void getAllItems() {
        Item item1 = new Item();
        item1.setNameOfItem("MangoCake");
        item1.setPriceOfItem(800);
        item1.setNumOfItem(2);

        Item item2 = new Item();
        item2.setNameOfItem("AppleCake");
        item2.setPriceOfItem(550);
        item2.setNumOfItem(1);

        Item item3 = new Item();
        item3.setNameOfItem("ChocolateCake");
        item3.setPriceOfItem(700);
        item3.setNumOfItem(1);

        Mockito.when(itemRepository.findAll()).thenReturn(List.of(item1, item2, item3));
        ItemService itemService = new ItemService(shopCartRepository, itemRepository);
        Assertions.assertEquals(3, itemService.getAllItems().size());
        Assertions.assertEquals("AppleCake", itemService.getAllItems().get(2).getNameOfItem());
    }
}