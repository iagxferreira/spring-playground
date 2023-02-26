package com.iagxferreira.springplayground.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("authenticated")
class AuthenticationTestController {

    @GetMapping("admin")
    fun admin(){

    }

    @GetMapping("user")
    fun user(){

    }
}