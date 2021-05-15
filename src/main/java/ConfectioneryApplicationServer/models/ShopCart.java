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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "listOfItems")
    private String listOfItems;
    @OneToMany(mappedBy = "shopCart", fetch = FetchType.LAZY)
    private List<Item> items;
}