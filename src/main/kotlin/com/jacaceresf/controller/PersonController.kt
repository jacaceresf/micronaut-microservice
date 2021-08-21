package com.jacaceresf.controller

import br.com.fluentvalidator.Validator
import com.jacaceresf.model.Person
import com.jacaceresf.validator.PersonValidator
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.LocalDate

@Controller
class PersonController {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)
    private val validator: Validator<Person> = PersonValidator()

    @Post(uri = "/person/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun createPersonMock(person: Person): HttpResponse<Any> {

        logger.info("Processing request {$person}")
        val validationResult = validator.validate(person)

        return if (validationResult.isValid) {
            val result = Person(
                id = Int.MAX_VALUE,
                name = person.name,
                phoneNumber = person.phoneNumber,
                email = person.email
            )
            HttpResponse.status<Any>(HttpStatus.OK)
                .body<Any>(result)
                .headers {
                    "Time" to LocalDate.now()
                }
        } else {
            HttpResponse.status<Any>(HttpStatus.BAD_REQUEST).body<Any>(validationResult.errors)
        }
    }

}