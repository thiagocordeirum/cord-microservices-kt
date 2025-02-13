package br.com.cord.controller

import br.com.cord.controller.request.PostBookRequest
import br.com.cord.controller.request.PutBookRequest
import br.com.cord.controller.response.BookResponse
import br.com.cord.service.BookService
import br.com.cord.service.CustomerService
import br.com.cord.extension.toBookModel
import br.com.cord.extension.toBookResponse
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable
import org.springframework.data.domain.Page
import org.springframework.data.web.PageableDefault

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/books")
class BookController(
    val bookService: BookService,
    val customerService: CustomerService
) {

    @GetMapping
    fun getAll(@PageableDefault(page = 0, size = 10)pageable: org.springframework.data.domain.Pageable, @RequestParam name: String?): Page<BookResponse> {
        return bookService.getAll(pageable,name).map {it.toBookResponse()}
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createBook(@RequestBody book: PostBookRequest){
        val customer = customerService.getCustomerById(book.customerId)
        bookService.createBook(book.toBookModel(customer))
    }


    @GetMapping("/{id}")
    fun getBookById(@PathVariable id: Int): BookResponse {
        return bookService.getBookById(id).toBookResponse()
    }


    @GetMapping("/actives")
    fun findActives(): List<BookResponse>{
        return bookService.findActives().map{it.toBookResponse()}
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateBook(@PathVariable id: Int, @RequestBody book: PutBookRequest) {
        val bookSaved = bookService.getBookById(id)
        bookService.updateBook(book.toBookModel(bookSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBook(@PathVariable id: Int){
        bookService.deleteBook(id)
    }
}