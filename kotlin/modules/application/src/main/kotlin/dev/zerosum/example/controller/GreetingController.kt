package dev.zerosum.example.controller

import dev.zerosum.example.greeting.GreetingService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingController(
        private val greetingService: GreetingService
) {

    @RequestMapping("/")
    fun hello(): ResponseEntity<String> {
        return ResponseEntity.ok(greetingService.greet())
    }
}