package br.com.cord.service

import br.com.cord.enums.CustomerStatus
import br.com.cord.model.CustomerModel
import br.com.cord.repository.CustomerRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val bookService: BookService
){

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return customerRepository.findByNameContaining(it)
        }
        return customerRepository.findAll().toList()
    }

    fun createCustomer(customer: CustomerModel) {
        customerRepository.save(customer)
    }

    fun getCustomerById(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow{
            RuntimeException("Customer com ID $id não encontrado!")
        }
    }


    fun updateCustomer(customer: CustomerModel) {
        if (!customerRepository.existsById(customer.id!!)){
            throw Exception()
        }
        customerRepository.save(customer)
    }


    fun deleteCustomer(id: Int) {
        val customer = getCustomerById(id)

        bookService.deleteByCustomer(customer)

        customer.status = CustomerStatus.INATIVO
        customerRepository.save(customer)
    }


}
