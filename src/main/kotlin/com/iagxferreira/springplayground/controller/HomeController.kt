package com.iagxferreira.springplayground.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal


@RestController
class HomeController {
    @GetMapping
    fun home(principal: Principal): String {
        return "Hello, " + principal.name
    }
}