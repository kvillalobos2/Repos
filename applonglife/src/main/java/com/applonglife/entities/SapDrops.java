package com.applonglife.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sapDrop")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SapDrops implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false, length = 200)
    private String title;
    @Column(name = "summary", nullable = false)
    private String summary;
    @Column(name = "content", nullable = false)
    private String content;
/*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sapDrop_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private BigTree bigTree;

 */
}
