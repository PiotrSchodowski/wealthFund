package com.example.wealthFund.repository.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Position {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private float amount;
    private float commission;
    private float result;
    private float openingAssetPrice;
    private float endingAssetPrice;
    private Date positionOpeningDate;
    private Date positionEndingDate;
    private float openingCurrencyPrice;
    private float endingCurrencyPrice;

    private boolean isOpen;

    @ManyToOne
    private Asset asset;

}
