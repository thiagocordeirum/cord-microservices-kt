package br.com.cord.controller

import br.com.cord.controller.request.PostBookRequest
import br.com.cord.controller.request.PutBookRequest
import br.com.cord.service.BookService
import br.com.cord.service.CustomerService
import br.com.cord.extension.toBookModel
import br.com.cord.model.BookModel
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/books")
class BookController(
    val bookService: BookService,
    val customerService: CustomerService
) {

    @GetMapping
    fun getAll(@RequestParam name: String?): List<BookModel> {
        return bookService.getAll(name)
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createBook(@RequestBody book: PostBookRequest){
        val customer = customerService.getCustomerById(book.customerId)
        bookService.createBook(book.toBookModel(customer))
    }


    @GetMapping("/{id}")
    fun getBookById(@PathVariable id: Int): BookModel {
        return bookService.getBookById(id)
    }


    @GetMapping("/actives")
    fun findActives(): List<BookModel>{
        return bookService.findActives()
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