package br.com.cord.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import java.math.BigDecimal

class PutBookRequest(
    var name: String?,
    var price: BigDecimal?,
)
