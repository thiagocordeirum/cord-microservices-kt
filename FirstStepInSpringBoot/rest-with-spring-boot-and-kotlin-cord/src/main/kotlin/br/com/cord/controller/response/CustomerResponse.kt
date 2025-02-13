package br.com.cord.controller.response

import br.com.cord.enums.CustomerStatus
import jakarta.persistence.*


data class CustomerResponse (
    var id: Int? = null,

    var name: String,

    var email: String,

    var status: CustomerStatus
)

