package com.shoppinglist.shoppinglist2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String product;
    private String amount;
    private boolean purchased;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

//    CommandLineRunner 상에서 사용할 간단한 사용자 정의
    public ShoppingItem(String product, String amount, boolean purchased, User user) {
        this.product = product;
        this.amount = amount;
        this.purchased = purchased;
        this.user = user;
    }
}
