package br.com.cord.repository

import br.com.cord.model.CustomerModel
import org.springframework.data.repository.CrudRepository

interface CustomerRepository: CrudRepository<CustomerModel, Int> {
    fun findByNameContaining(name: String): List<CustomerModel>
}