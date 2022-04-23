package com.kartik.curd.service.impl;

import com.kartik.curd.entity.Address;
import com.kartik.curd.entity.Company;
import com.kartik.curd.entity.Employee;
import com.kartik.curd.repository.AddressRepository;
import com.kartik.curd.repository.CompanyRepository;
import com.kartik.curd.repository.EmployeeRepository;
import com.kartik.curd.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    public ResponseEntity<Company> addCompany(Company company) {

        try {

            /// TODO: one approach for OneToOne
            //Address address = company.getCompanyAddress();
            //address.setCompany(company);
            return new ResponseEntity<>(companyRepository.save(company), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Company> updateCompany(Long id, Company company) {
        try {
            Company company1 = companyRepository.getById(id);
            company1.setCompanyName(company.getCompanyName());
            return new ResponseEntity<>(companyRepository.save(company1), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Address> deleteAddress(Long id) {
        try {

            Address address = addressRepository.getById(id);
            address.setCompany(null);
            addressRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Company> deleteCompany(Long id) {
        try {
            companyRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Employee> addEmp(Employee employee) {
        try {
            return new ResponseEntity<>(employeeRepository.save(employee), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Employee> updateEmp(Long id, Employee employee) {
        try {
            Employee employee1 = employeeRepository.getById(id);
            employee1.setName(employee.getName());
            return new ResponseEntity<>(employeeRepository.save(employee1), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Address> addAddress(Address address) {
        System.out.println("CommonServiceImpl.addAddress");
        System.out.println(address.toString());
        try {
            return new ResponseEntity<>(addressRepository.save(address), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}