package com.BookStoreApi.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private int[] orders;

    private Float price;
}
