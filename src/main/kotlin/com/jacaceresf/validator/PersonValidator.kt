package com.jacaceresf.validator

import br.com.fluentvalidator.AbstractValidator
import br.com.fluentvalidator.predicate.ObjectPredicate.nullValue
import com.jacaceresf.model.Resource
import br.com.fluentvalidator.predicate.ObjectPredicate.nullValue
import br.com.fluentvalidator.predicate.StringPredicate
import br.com.fluentvalidator.predicate.StringPredicate.*
import com.jacaceresf.model.Person


class PersonValidator() : AbstractValidator<Person>() {

    val EMAIL_REGEX = """^([\w\.\-]+)@([\w\-]+)((\.(\w){2,3})+)$"""

    override fun rules() {

        ruleFor(Person::email)
            .must(stringMatches(EMAIL_REGEX))
            .withMessage("Person's email is not valid!")
            .withFieldName("email")
            .withAttempedValue(Person::email)
            .critical()

        ruleFor(Person::phoneNumber)
            .must(stringSize(15))
            .withMessage("Phone number is not valid")
            .withFieldName("phoneNumber")
            .critical()

        ruleFor(Person::name)
            .must(StringPredicate.stringSizeGreaterThan(0))
            .withMessage("Person name can't be empty!")
            .withFieldName("person's name")
            .withAttempedValue(Person::name)
            .critical()
    }

}