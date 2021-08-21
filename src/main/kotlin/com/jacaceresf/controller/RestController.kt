package com.jacaceresf.controller

import br.com.fluentvalidator.Validator
import com.jacaceresf.model.Resource
import com.jacaceresf.service.LoadSimulator
import com.jacaceresf.validator.ResourceValidator
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import jakarta.inject.Inject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*


@Controller
class RestController {

    @Inject
    lateinit var loadSimulator: LoadSimulator

    private val logger: Logger = LoggerFactory.getLogger(javaClass)
    private val validator: Validator<Resource> = ResourceValidator()

    @Get(uri = "/resource/{resourceId}")
    @Produces(MediaType.APPLICATION_JSON)
    suspend fun loadSimulator(resourceId: String): HttpResponse<Any> {

        val correlationId = UUID.randomUUID()
        val httpResponse: HttpResponse<Any>

        logger.info("Processing request {$correlationId}")

        val validationResult = validator.validate(Resource(resourceId))

        httpResponse = if (validationResult.isValid) {
            val response = loadSimulator.processLoad()
            HttpResponse.status<Any>(HttpStatus.OK)
                .body<Any>(response)
        } else {
            HttpResponse.status<Any>(HttpStatus.BAD_REQUEST).body<Any>(validationResult.errors)
        }

        logger.info("Finished processing {$correlationId}")
        return httpResponse
    }
}