package br.com.cord

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class TestController {

        @GetMapping("/home")
        fun home(): String {
            return "index" // Retorna a página index.html
        }
    }