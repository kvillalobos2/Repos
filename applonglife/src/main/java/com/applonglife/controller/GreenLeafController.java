package com.applonglife.controller;

import com.applonglife.entities.BigTree;
import com.applonglife.entities.GreenLeaf;
import com.applonglife.service.IBigTreeService;
import com.applonglife.service.IGreenLeafService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/greenLeaves")
@Api(tags="GreenLeaf", value = "RESTful de Green Leaf")
public class GreenLeafController {
    @Autowired
    private IGreenLeafService greenLeafService;
    @Autowired
    private IBigTreeService bigTreeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listar Green Leaf", notes = "Método para listar Green Leaf")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Green Leaf encontrado"),
            @ApiResponse(code = 404, message = "Green Leaf no encontrado")
    })
    public ResponseEntity<List<GreenLeaf>> findAllGreenLeaf(){
        try {
            List<GreenLeaf> greenLeaves = greenLeafService.getAll();
            if (greenLeaves.size()>0){
                return new ResponseEntity<List<GreenLeaf>>(greenLeaves, HttpStatus.OK);
            }else{
                return new ResponseEntity<List<GreenLeaf>>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex){
            return new ResponseEntity<List<GreenLeaf>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Green leaf", notes = "Método para buscar Big leaf por id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Green leaf encontrado"),
            @ApiResponse(code = 404, message = "Green leaf no encontrado")
    })
    public ResponseEntity<GreenLeaf> findGreenLeafById(@PathVariable("id")Long id){
        try {
            Optional<GreenLeaf> greenLeaf = greenLeafService.getById(id);
            if (!greenLeaf.isPresent()){
                return new ResponseEntity<GreenLeaf>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<GreenLeaf>(greenLeaf.get(),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<GreenLeaf>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value ="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de un Green Leaf de un Big Tree", notes ="Método que registra un green leaf" )
    @ApiResponses({
            @ApiResponse(code=201, message = "Booking creado"),
            @ApiResponse(code=404, message = "Booking no creado")
    })
    public ResponseEntity<GreenLeaf> insertGreenLeaf(@PathVariable("id") Long id, @Valid @RequestBody GreenLeaf greenLeaf){
        try{
            Optional<BigTree> bigTree = bigTreeService.getById(id);
            if(bigTree.isPresent()){
                greenLeaf.setBigtree(bigTree.get());
                GreenLeaf greenLeafNew= greenLeafService.save(greenLeaf);
                return ResponseEntity.status(HttpStatus.CREATED).body(greenLeafNew);
            }
            else
                return new ResponseEntity<GreenLeaf>(HttpStatus.FAILED_DEPENDENCY);
        }catch (Exception ex){
            return new ResponseEntity<GreenLeaf>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Actualización de datos de Green Leaf", notes = "Método que actualizar los datos de Green Leaf")
    @ApiResponses({
            @ApiResponse(code=200, message = "Datos de Green Leaf actualizados"),
            @ApiResponse(code=404, message = "Datos de Green Leaf no actualizados")
    })
    public ResponseEntity<GreenLeaf> updateGreenLeaf(@PathVariable("id") Long id, @Valid @RequestBody GreenLeaf greenLeaf){
        try{
            Optional<GreenLeaf> greenLeafOld = greenLeafService.getById(id);
            if(!greenLeafOld.isPresent())
                return new ResponseEntity<GreenLeaf>(HttpStatus.NOT_FOUND);
            else{
                greenLeaf.setId(id);
                greenLeafService.save(greenLeaf);
                return new ResponseEntity<GreenLeaf>(greenLeaf, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<GreenLeaf>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Eliminación de Green leaf por Id", notes = "Método que elimina los datos de un Green leaf")
    @ApiResponses({
            @ApiResponse(code=200, message = "Datos de Green leaf eliminados"),
            @ApiResponse(code=404, message = "Datos de Green leaf no eliminados")
    })
    public ResponseEntity<GreenLeaf> deleteGreenLeaf(@PathVariable("id") Long id){
        try{
            Optional<GreenLeaf> greenLeafDelete = greenLeafService.getById(id);
            if(greenLeafDelete.isPresent()){
                greenLeafService.delete(id);
                return new ResponseEntity<GreenLeaf>(HttpStatus.OK);
            }
            else
                return new ResponseEntity<GreenLeaf>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<GreenLeaf>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
