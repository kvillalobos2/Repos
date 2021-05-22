package com.applonglife.service.impl;

import com.applonglife.entities.BigTree;
import com.applonglife.repository.IBigTreeRepository;
import com.applonglife.repository.IGreenLeafRepository;
import com.applonglife.service.IBigTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BigTreeServiceImpl implements IBigTreeService {
    @Autowired
    private IBigTreeRepository bigTreeRepository;

    @Override
    @Transactional
    public BigTree save(BigTree bigTree) throws Exception {
        return bigTreeRepository.save(bigTree);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        bigTreeRepository.deleteById(id);
    }

    @Override
    public List<BigTree> getAll() throws Exception {
        return bigTreeRepository.findAll();
    }

    @Override
    public Optional<BigTree> getById(Long id) throws Exception {
        return bigTreeRepository.findById(id);
    }

    @Override
    public BigTree findByUsername(String username) throws Exception {
        return bigTreeRepository.findByUsername(username);
    }
}
