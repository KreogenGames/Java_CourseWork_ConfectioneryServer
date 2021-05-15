package ConfectioneryApplicationServer.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nameOfItem;
    @Column(nullable = false)
    private int priceOfItem;
    @Column(nullable = false)
    private int numOfItem;
    @ManyToOne
    @JoinColumn(name = "shopCart_id", nullable = false)
    private ShopCart shopCart;
}