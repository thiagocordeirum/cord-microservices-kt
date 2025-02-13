package br.com.cord.extension

import br.com.cord.controller.request.PostBookRequest
import br.com.cord.controller.request.PostCustomerRequest
import br.com.cord.controller.request.PutBookRequest
import br.com.cord.controller.request.PutCustomerRequest
import br.com.cord.controller.response.BookResponse
import br.com.cord.controller.response.CustomerResponse
import br.com.cord.enums.BookStatus
import br.com.cord.enums.CustomerStatus
import br.com.cord.model.BookModel
import br.com.cord.model.CustomerModel

fun PostCustomerRequest.toCustomerModel(): CustomerModel{
    return CustomerModel(name = this.name, email = this.email, status = CustomerStatus.ATIVO)
}

fun PutCustomerRequest.toCustomerModel(customer: CustomerModel): CustomerModel{
    return CustomerModel(id = customer.id, name = this.name?: customer.name, email = this.email?:customer.email, status = CustomerStatus.ATIVO)
}

fun PostBookRequest.toBookModel(customer: CustomerModel): BookModel {
    return BookModel(
        name = this.name,
        price = this.price,
        status = BookStatus.ATIVO,
        customer = customer,
    )
}

fun PutBookRequest.toBookModel(book: BookModel): BookModel{
    return BookModel(
        id = book.id,
        name = this.name ?: book.name,
        price = this.price ?: book.price,
        status = book.status,
        customer = book.customer,
    )
}

fun BookModel.toBookResponse(): BookResponse {
    return BookResponse(
        id = this.id,
        name = this.name,
        price = this.price,
        status = this.status,
        customer = this.customer
    )
}

fun CustomerModel.toCustomerResponse(): CustomerResponse{
    return CustomerResponse(
        id = this.id,
        name = this.name,
        email = this.email,
        status = this.status?: throw Exception("ID Nulo!")
    )
}
