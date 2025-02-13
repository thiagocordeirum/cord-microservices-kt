package br.com.cord.model

import br.com.cord.enums.BookStatus
import jakarta.persistence.*
import java.math.BigDecimal

@Entity (name = "book")
data class BookModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String,

    @Column
    var price: BigDecimal,

    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus?,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: CustomerModel? = null
){
    constructor() : this(name = "", price = BigDecimal.ZERO, status = BookStatus.ATIVO)
}

