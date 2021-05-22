package com.applonglife.repository;

import com.applonglife.entities.GreenLeaf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGreenLeafRepository extends JpaRepository<GreenLeaf, Long> {
    public GreenLeaf findByTitle(String title);
}
