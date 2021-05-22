package com.applonglife.controller;

import com.applonglife.entities.BigTree;
import com.applonglife.service.IBigTreeService;
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
@RequestMapping("/api/bigTrees")
@Api(tags="BigTree", value = "RESTful de Big Tree")
public class BigTreeController {
    @Autowired
    private IBigTreeService bigTreeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listar Big Trees", notes = "Método para listar Big Trees")
    @ApiResponses({
            @ApiResponse(code = 201, message = "BigTree encontrado"),
            @ApiResponse(code = 404, message = "BigTree no encontrado")
    })
    public ResponseEntity<List<BigTree>> findAll(){
        try {
            List<BigTree> bigTrees = bigTreeService.getAll();
            if (bigTrees.size()>0){
                return new ResponseEntity<List<BigTree>>(bigTrees,HttpStatus.OK);
            }else{
                return new ResponseEntity<List<BigTree>>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex){
            return new ResponseEntity<List<BigTree>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Big Trees", notes = "Método para buscar Big Tree por id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "BigTree encontrado"),
            @ApiResponse(code = 404, message = "BigTree no encontrado")
    })
    public ResponseEntity<BigTree> findById(@PathVariable("id")Long id){
        try {
            Optional<BigTree> bigTree = bigTreeService.getById(id);
            if (!bigTree.isPresent()){
                return new ResponseEntity<BigTree>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<BigTree>(bigTree.get(),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<BigTree>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByUsername/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar username de un Big Tree", notes = "Método para buscar Big Tree por username")
    @ApiResponses({
            @ApiResponse(code = 201, message = "BigTree encontrado"),
            @ApiResponse(code = 404, message = "BigTree no encontrado")
    })
    public ResponseEntity<BigTree> findByUsername(@PathVariable("username")String username){
        try {
            BigTree bigTree = bigTreeService.findByUsername(username);
            if (bigTree == null){
                return new ResponseEntity<BigTree>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<BigTree>(bigTree,HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<BigTree>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Ingreso de Big Tree", notes = "Método para registrar un Big Tree")
    @ApiResponses({
            @ApiResponse(code = 201, message = "BigTree ingresado"),
            @ApiResponse(code = 404, message = "BigTree no ingresado")
    })
    public ResponseEntity<BigTree> insertBigTree(@RequestBody BigTree bigTree){
        try {
            BigTree bigTreeNew = bigTreeService.save(bigTree);
            return ResponseEntity.status(HttpStatus.CREATED).body(bigTreeNew);
        }catch (Exception ex){
            return new ResponseEntity<BigTree>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Actualizar Big Tree", notes = "Método para actualizar un Big Tree")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Big Tree actualizado"),
            @ApiResponse(code = 404, message = "Big Tree no actualizado")
    })
    public ResponseEntity<BigTree> updateBigTree(@PathVariable("id") Long id, @Valid @RequestBody BigTree bigTree){
        try {
            Optional<BigTree> bigTreeOld = bigTreeService.getById(id);
            if (!bigTreeOld.isPresent()){
                return new ResponseEntity<BigTree>(HttpStatus.NOT_FOUND);
            }
            bigTree.setId(id);
            bigTreeService.save(bigTree);
            return new ResponseEntity<BigTree>(bigTree,HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<BigTree>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminar un Big Tree", notes = "Método que elimina un Big Tree")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Big Tree eliminado"),
            @ApiResponse(code = 404, message = "Big Tree no eliminado")
    })
    public ResponseEntity<BigTree> deleteBigTree(@PathVariable("id") Long id){
        try {
            Optional<BigTree> bigTreeDelete = bigTreeService.getById(id);
            if (!bigTreeDelete.isPresent()){
                return new ResponseEntity<BigTree>(HttpStatus.NOT_FOUND);
            }
            bigTreeService.delete(id);
            return new ResponseEntity<BigTree>(HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<BigTree>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
