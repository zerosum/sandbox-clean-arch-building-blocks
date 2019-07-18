package dev.zerosum.example.controller

import dev.zerosum.example.greeting.GreetingService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingController(
        val greetingService: GreetingService
) {

    @RequestMapping("/")
    fun hello(): String {
        return greetingService.greet()
    }
}