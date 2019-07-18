package dev.zerosum.example.greeting

import javax.inject.Named

interface GreetingService {
    fun greet(): String
}

@Named
class GreetingServiceImpl : GreetingService {
    override fun greet(): String {
        return "Hello, World!!"
    }
}