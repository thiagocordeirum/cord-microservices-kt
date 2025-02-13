package br.com.cord.service

import br.com.cord.enums.BookStatus
import br.com.cord.model.BookModel
import br.com.cord.model.CustomerModel
import br.com.cord.repository.BookRepository
import org.hibernate.query.Page
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository,
) {

    fun getAll(pageable: org.springframework.data.domain.Pageable, name: String?): org.springframework.data.domain.Page<BookModel> {
        name?.let {
            return bookRepository.findByNameContaining(it, pageable)
        }
        return bookRepository.findAll(pageable)
    }

    fun createBook(book: BookModel){
         bookRepository.save(book)
    }

    fun getBookById(id: Int): BookModel {
        return bookRepository.findById(id).orElseThrow {
            RuntimeException("Livro com ID $id não encontrado!")
        }
    }



    fun updateBook(book: BookModel) {
        book.id?.let {
            if (!bookRepository.existsById(it)) {
                throw RuntimeException("Livro com ID $it não encontrado para atualização.")
            }
            bookRepository.save(book)
        } ?: throw RuntimeException("O ID do livro não pode ser nulo!")
    }


    fun deleteBook(id: Int) {
        val book = getBookById(id)

        if(book.status == BookStatus.DELETADO){
            throw Exception("Status ${BookStatus.DELETADO} não pode ser alterado")
        }

        book.status = BookStatus.CANCELADO

        bookRepository.save(book)
    }

    fun findActives(): List<BookModel> {
        return bookRepository.findByStatus(BookStatus.ATIVO)
    }


    fun deleteByCustomer(customer: CustomerModel) {
        val books = bookRepository.findByCustomer(customer)

        for(book in books){
            book.status = BookStatus.DELETADO
        }

        bookRepository.saveAll(books)
    }
}