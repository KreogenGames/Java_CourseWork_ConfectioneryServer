package ConfectioneryApplicationServer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ConfectioneryApplicationServer.models.ShopCart;

@Repository
public interface ShopCartRepository extends JpaRepository<ShopCart, Long> {
    ShopCart findByShopCartId(Long shopCartId);
    //ShopCart findByUser(String ownerName);
}