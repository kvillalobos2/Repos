package com.applonglife.service.impl;

import com.applonglife.entities.GreenLeaf;
import com.applonglife.repository.IGreenLeafRepository;
import com.applonglife.service.IGreenLeafService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GreenLeafServiceImpl implements IGreenLeafService {
    @Autowired
    private IGreenLeafRepository greanleafRepository;

    @Override
    @Transactional
    public GreenLeaf save(GreenLeaf greenLeaf) throws Exception {
        return greanleafRepository.save(greenLeaf);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        greanleafRepository.deleteById(id);
    }

    @Override
    public List<GreenLeaf> getAll() throws Exception {
        return greanleafRepository.findAll();
    }

    @Override
    public Optional<GreenLeaf> getById(Long id) throws Exception {
        return greanleafRepository.findById(id);
    }

    @Override
    public GreenLeaf findByTitle(String title) throws Exception {
        return greanleafRepository.findByTitle(title);
    }
}
