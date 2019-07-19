package dev.zerosum.example.controller

import dev.zerosum.example.greeting.GreetingService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/")
class GreetingController(
        private val greetingService: GreetingService
) {

    @Get
    fun hello(): String {
        return greetingService.greet()
    }
}