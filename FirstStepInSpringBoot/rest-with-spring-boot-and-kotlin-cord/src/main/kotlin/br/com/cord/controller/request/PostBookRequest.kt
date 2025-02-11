package br.com.cord.controller.request

import br.com.cord.enums.BookStatus
import br.com.cord.model.CustomerModel
import com.fasterxml.jackson.annotation.JsonAlias
import java.math.BigDecimal

data class PostBookRequest(
    var name: String,
    var price: BigDecimal,

    @JsonAlias("customer_id")
    var customerId: Int
)