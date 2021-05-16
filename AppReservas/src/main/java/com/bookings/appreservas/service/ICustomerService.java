package com.bookings.appreservas.service;

import com.bookings.appreservas.entities.Customer;

import java.util.List;


public interface ICustomerService extends CrudService<Customer> {
    public Customer findByDni(String dni) throws Exception;
    public List<Customer> findByLastName(String lastname) throws Exception;
    public List<Customer> findByFirstNameAndLastName(String firstname, String lastname) throws Exception;
    public List<Customer> findByFirstName(String firstname) throws Exception;
}
