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
public class Dividend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private float value;
    private Date date;
}
