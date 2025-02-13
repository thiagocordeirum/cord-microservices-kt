package br.com.cord.repository

import br.com.cord.enums.BookStatus
import br.com.cord.model.BookModel
import br.com.cord.model.CustomerModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository

interface BookRepository: CrudRepository<BookModel, Int> {
//    fun findByNameContaining(name: String): List<BookModel>
    abstract fun findByStatus(ativo: BookStatus): List<BookModel>
    abstract fun findByCustomer(customer: CustomerModel): List<BookModel>
    fun findAll(pageable: Pageable): Page<BookModel>
    fun findByNameContaining(name: String, pageable: Pageable): Page<BookModel>
}