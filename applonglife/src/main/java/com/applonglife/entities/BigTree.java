package com.applonglife.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "bigTree")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BigTree implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "firstname", nullable = false)
    private String firstname;
    @Column(name = "lastname", nullable = false)
    private String lastname;
    @Column(name = "gender", nullable = false)
    private String gender;
    @Column(name = "bornAt", nullable = false)
    //@Temporal(TemporalType.DATE) //Para que muestre solo dd-mm-yyyy
    private String bornAt;
}
