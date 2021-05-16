package com.bookings.appreservas.controller;

import com.bookings.appreservas.entities.Booking;
import com.bookings.appreservas.entities.Customer;
import com.bookings.appreservas.service.IBookingService;
import com.bookings.appreservas.service.ICustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
@Api(tags="Booking", value = "Servicio Web RESTFul de Bookings")
public class BookingController {

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IBookingService bookingService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listar Bookings", notes="Método para listar todos los bookings")
    @ApiResponses({
            @ApiResponse(code=201, message = "Bookings encontrados"),
            @ApiResponse(code=404, message = "Bookings no encontrados")
    })
    public ResponseEntity<List<Booking>>findAllBookings(){
        try{
            List<Booking> bookings = bookingService.getAll();
            if(bookings.size()>0)
                return new ResponseEntity<List<Booking>>(bookings,HttpStatus.OK);
            else
                return new ResponseEntity<List<Booking>>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<List<Booking>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Booking por Id", notes="Método para listar un booking por Id")
    @ApiResponses({
            @ApiResponse(code=201, message = "Booking encontrado"),
            @ApiResponse(code=404, message = "Booking no encontrado")
    })
    public ResponseEntity<Booking>findBookingById(@PathVariable("id") Long id){
        try{
            Optional<Booking> booking= bookingService.getById(id);
            if(!booking.isPresent())
                return new ResponseEntity<Booking>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<Booking>(booking.get(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Booking>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchBetweenDates", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Bookings entre fechas", notes="Método para listar bookings entre fechas")
    @ApiResponses({
            @ApiResponse(code=201, message = "Bookings encontrados"),
            @ApiResponse(code=404, message = "Bookings no encontrados")
    })
    public ResponseEntity<List<Booking>> findBookingsBetweenDates(@RequestParam(name="checking_date") String checking_string,
                                                                  @RequestParam(name="checkout_date") String checkout_string){
        try{
            Date checking_date = ParseDate(checking_string);
            Date checkout_date = ParseDate(checkout_string);
            List<Booking> bookings = bookingService.find(checking_date,checkout_date);
            if(bookings.size()>0)
                return new ResponseEntity<List<Booking>>(bookings,HttpStatus.OK);
            else
                return new ResponseEntity<List<Booking>>(HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            return new ResponseEntity<List<Booking>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static Date ParseDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date result = null;
        try{
            result = format.parse(date);
        }catch (Exception ex){
        }
        return result;
    }

    @PostMapping(value ="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de un Booking de un Customer", notes ="Método que registra un booking" )
    @ApiResponses({
            @ApiResponse(code=201, message = "Booking creado"),
            @ApiResponse(code=404, message = "Booking no creado")
    })
    public ResponseEntity<Booking> insertBooking(@PathVariable("id") Long id, @Valid @RequestBody Booking booking){
        try{
            Optional<Customer> customer = customerService.getById(id);
            if(customer.isPresent()){
                booking.setCustomer(customer.get());
                Booking bookingNew = bookingService.save(booking);
                return ResponseEntity.status(HttpStatus.CREATED).body(bookingNew);
            }
            else
                return new ResponseEntity<Booking>(HttpStatus.FAILED_DEPENDENCY);
        }catch (Exception ex){
            return new ResponseEntity<Booking>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Actualización de datos de Booking", notes = "Método que actualizar los datos de Booking")
    @ApiResponses({
            @ApiResponse(code=200, message = "Datos de Booking actualizados"),
            @ApiResponse(code=404, message = "Booking no encontrado")
    })
    public ResponseEntity<Booking> updateBooking(@PathVariable("id") Long id, @Valid @RequestBody Booking booking){
        try{
            Optional<Booking> bookingOld = bookingService.getById(id);
            if(!bookingOld.isPresent())
                return new ResponseEntity<Booking>(HttpStatus.NOT_FOUND);
            else{
                booking.setId(id);
                bookingService.save(booking);
                return new ResponseEntity<Booking>(booking, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<Booking>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Eliminación de Booking por Id", notes = "Método que elimina los datos de un Booking")
    @ApiResponses({
            @ApiResponse(code=200, message = "Datos de Booking eliminados"),
            @ApiResponse(code=404, message = "Booking no encontrado")
    })
    public ResponseEntity<Booking> deleteBooking(@PathVariable("id") Long id){
        try{
            Optional<Booking> bookingDelete = bookingService.getById(id);
            if(bookingDelete.isPresent()){
                bookingService.delete(id);
                return new ResponseEntity<Booking>(HttpStatus.OK);
            }
            else
                return new ResponseEntity<Booking>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<Booking>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
