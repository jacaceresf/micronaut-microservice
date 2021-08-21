package com.jacaceresf

import io.micronaut.runtime.Micronaut.*
import io.swagger.v3.oas.annotations.*
import io.swagger.v3.oas.annotations.info.*

@OpenAPIDefinition(
    info = Info(
        title = "micronaut-microservice test",
        version = "0.0.1",
        contact = Contact(
            name = "Jorge Caceres Flores",
            email = "jorge97caceres@gmail.com",
            url = "https://github.com/jacaceresf"
        )
    )
)
object Api {
}

fun main(args: Array<String>) {
    build()
        .args(*args)
        .packages("com.jacaceresf")
        .start()
}

