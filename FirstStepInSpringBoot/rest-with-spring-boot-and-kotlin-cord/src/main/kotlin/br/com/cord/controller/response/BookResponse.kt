package br.com.cord.controller.response

import br.com.cord.enums.BookStatus
import br.com.cord.model.CustomerModel
import jakarta.persistence.*
import java.math.BigDecimal

class BookResponse (
    var id: Int? = null,

    var name: String,

    var price: BigDecimal,

    var status: BookStatus?,

    var customer: CustomerModel? = null
)
