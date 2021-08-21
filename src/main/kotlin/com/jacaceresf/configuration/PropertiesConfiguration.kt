package com.jacaceresf.configuration

import io.micronaut.context.annotation.ConfigurationProperties

/**
 * This class will be used to load some parameters from configuration file.
 */
@ConfigurationProperties("microservice")
class PropertiesConfiguration {

    var processingTime: Long = 0
    var threads: Long = 0
    var returnMessage: String = ""

}