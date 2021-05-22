package com.applonglife.service;

import com.applonglife.entities.BigTree;

public interface IBigTreeService extends CrudService<BigTree>{
    public BigTree findByUsername(String username) throws Exception;
}
