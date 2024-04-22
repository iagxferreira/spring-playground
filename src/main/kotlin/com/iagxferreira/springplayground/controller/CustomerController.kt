package com.iagxferreira.springplayground.controller

import com.iagxferreira.springplayground.model.Customer
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class CustomerController {

    @GetMapping
    fun getCustomer(): Customer {
        return Customer(
            id = UUID.randomUUID().toString(),
            email = "teste@teste.com",
            name = "Teste"
        )
    }
}