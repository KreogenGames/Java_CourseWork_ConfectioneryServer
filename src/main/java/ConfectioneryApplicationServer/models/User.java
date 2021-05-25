package ConfectioneryApplicationServer.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String firstName;
    @Column
    private String middleName;*/
    @Column(nullable = false)
    private String username;
    @JsonIgnore
    private String password;

    //////////////////////////Не трогать
    /*@OneToOne
    @JoinColumn(name = "shopCart_id")
    private ShopCart shopCart;*/
    //////////////////////////
}