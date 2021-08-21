package com.jacaceresf.validator

import br.com.fluentvalidator.AbstractValidator
import br.com.fluentvalidator.predicate.StringPredicate.stringMatches
import com.jacaceresf.model.Resource
import br.com.fluentvalidator.predicate.ObjectPredicate.nullValue


class ResourceValidator() : AbstractValidator<Resource>() {

    private val UUID_REGEX: String = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}"

    override fun rules() {

        ruleFor(Resource::id)
            .must(stringMatches(UUID_REGEX))
            .withMessage("The resource Id is not valid")
            .withFieldName("id")
            .critical()
    }

}