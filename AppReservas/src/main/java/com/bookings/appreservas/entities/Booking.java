package com.bookings.appreservas.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name="description", nullable = true, length = 200)
    private String description;
    @Column(name="number_persons", nullable = false)
    private int numberPersons;
    @Column(name="create_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    @Temporal(TemporalType.DATE)
    @Column(name="checkin_date", nullable = false)
    private Date checkinDate;
    @Temporal(TemporalType.DATE)
    @Column(name="checkout_date",nullable = false)
    private Date checkoutDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Customer customer;
}
