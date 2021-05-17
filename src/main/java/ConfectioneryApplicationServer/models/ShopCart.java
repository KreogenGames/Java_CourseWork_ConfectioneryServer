package ConfectioneryApplicationServer.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "shopCart_table")
public class ShopCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy = "shopCart", fetch = FetchType.LAZY)
    private List<Item> items;
    //При создании пользователя параллельно создаю корзину
    @OneToOne(mappedBy = "shopCart")
    private User user;
}