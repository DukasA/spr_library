package com.example.librarysystem.controllers;

import com.example.librarysystem.dto.book.BookCreateRequest;
import com.example.librarysystem.dto.book.BookCreateResponse;
import com.example.librarysystem.dto.book.BookListResponse;
import com.example.librarysystem.dto.rental.RentalCreateResponse;
import com.example.librarysystem.dto.rental.RentalListResponse;
import com.example.librarysystem.enums.BookStatus;
import com.example.librarysystem.service.book.BookService;
import com.example.librarysystem.service.rental.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequiredArgsConstructor
@RequestMapping("/library")
public class LibraryController {

    private final BookService bookService;
    private final RentalService rentalService;

    @GetMapping("/all")
    public String getAllBooks(@RequestParam(required = false) BookStatus status, Model model) {
        BookListResponse list = bookService.list(status);
        model.addAttribute("books", list);
        return "all-books";
    }

    @PostMapping("/add-book")
    public String create(@ModelAttribute("book") BookCreateRequest request, RedirectAttributes redirectAttributes) {
        BookCreateResponse response = bookService.create(request);
        if (response != null) {
            redirectAttributes.addFlashAttribute("successMessage", "Book was successfully added!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add the book!");
        }
        return "redirect:/library/all";
    }

    @GetMapping("/add-book")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new BookCreateRequest());
        return "add-book";
    }

    @GetMapping("/rentals")
    public String getAllRentals(@RequestParam(required = false) boolean overdueOnly, Model model) {
        RentalListResponse list = rentalService.list(overdueOnly);
        model.addAttribute("rentals", list);
        return "rentals";
    }

    @PostMapping("/rent/{bookId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String create (@PathVariable String bookId, RedirectAttributes redirectAttributes){
        Long parsedBookId = Long.parseLong(bookId);
        RentalCreateResponse response = rentalService.create(parsedBookId);
        if (response != null) {
            redirectAttributes.addFlashAttribute("successMessage", "Book was successfully rented!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to rent the book!");
        }
        return "redirect:/library/all";
    }

}
