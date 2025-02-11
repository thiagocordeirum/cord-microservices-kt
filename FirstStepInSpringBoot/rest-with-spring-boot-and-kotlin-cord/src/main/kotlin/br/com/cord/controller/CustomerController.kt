package br.com.cord.controller

import br.com.cord.controller.request.PostCustomerRequest
import br.com.cord.controller.request.PutCustomerRequest
import br.com.cord.extension.toCustomerModel
import br.com.cord.model.CustomerModel
import br.com.cord.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customers")
class CustomerController(
    val customerService: CustomerService
) {

    @GetMapping
    fun getAll(@RequestParam name: String?): List<CustomerModel> {
        return customerService.getAll(name)
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(@RequestBody customer: PostCustomerRequest) {
        customerService.createCustomer(customer.toCustomerModel())
    }


    @GetMapping("/{id}")
    fun getCustomerById(@PathVariable id: Int): CustomerModel{
        return customerService.getCustomerById(id)
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateCustomer(@PathVariable id: Int, @RequestBody customer: PutCustomerRequest) {
        customerService.updateCustomer(customer.toCustomerModel(id))
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id: Int) {
        customerService.deleteCustomer(id)
    }
}
