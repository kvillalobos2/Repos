package com.bookings.appreservas.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name="Customer.findByFirstName",query = "Select c from Customer c where c.firstName= ?1")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="firstname", nullable = false, length = 50)
    private String firstName;
    @Column(name="lastname", nullable = false, length = 50)
    private String lastName;
    @Column(name="dni", nullable = false, length = 8)
    private String dni;
    @Column(name="address", nullable = true, length = 150)
    private String address;
    @Column(name="phone", nullable = true, length = 9)
    private String phone;
    @Column(name="email", nullable = true, length = 50)
    private String email;
}
