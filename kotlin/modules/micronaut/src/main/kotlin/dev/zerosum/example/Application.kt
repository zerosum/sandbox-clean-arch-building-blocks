package dev.zerosum.example

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("dev.zerosum.example")
                .mainClass(Application.javaClass)
                .start()
    }
}