package com.applonglife.repository;

import com.applonglife.entities.BigTree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBigTreeRepository extends JpaRepository <BigTree, Long>{
    public BigTree findByUsername(String username);

}
