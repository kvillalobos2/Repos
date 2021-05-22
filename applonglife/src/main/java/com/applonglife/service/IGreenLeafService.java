package com.applonglife.service;

import com.applonglife.entities.GreenLeaf;

public interface IGreenLeafService extends CrudService<GreenLeaf>{
    //Luego copiamos lo que está en iRepository con throws Exception
    public GreenLeaf findByTitle(String title) throws Exception;
}
