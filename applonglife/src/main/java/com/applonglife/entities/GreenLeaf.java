package com.applonglife.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "greenLeaf")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GreenLeaf implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "scenario", nullable = false)
    private String scenario;
    @Column(name = "tip", nullable = false)
    private String tip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "greenLeaf_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private BigTree bigtree;
}
