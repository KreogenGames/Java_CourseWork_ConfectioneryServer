package ConfectioneryApplicationServer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ConfectioneryApplicationServer.models.ShopCart;
import ConfectioneryApplicationServer.models.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, ItemFilter {
    void deleteAllByShopCart(ShopCart shopCart);
}