package com.jacaceresf

import br.com.fluentvalidator.Validator
import br.com.fluentvalidator.context.ValidationResult
import com.jacaceresf.model.Resource
import com.jacaceresf.validator.ResourceValidator
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Test
import java.util.*

@MicronautTest
internal class ResourceValidatorTest {

    @Test
    internal fun shouldValidateIdAsValidUUID() {

        val validator: Validator<Resource> = ResourceValidator()
        val resource = Resource(UUID.randomUUID().toString())

        val result: ValidationResult = validator.validate(resource)

        assert(result.isValid)
        assert(result.errors.isEmpty())
    }

}