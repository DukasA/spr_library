package com.example.librarysystem.service.rental;

import com.example.librarysystem.dto.rental.RentalCreateRequest;
import com.example.librarysystem.dto.rental.RentalCreateResponse;
import com.example.librarysystem.dto.rental.RentalListResponse;
import com.example.librarysystem.entity.Book;
import com.example.librarysystem.entity.Rental;
import com.example.librarysystem.entity.User;
import com.example.librarysystem.enums.BookStatus;
import com.example.librarysystem.repository.BookRepository;
import com.example.librarysystem.repository.RentalRepository;
import com.example.librarysystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService{
    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Override
    public RentalCreateResponse create(Long bookId) {
        User user = getIfExistOrThrow(getUserId());
        Book book = getIfExistBookOrThrow(bookId);
        Rental rental = new Rental();
        rental.setUser(user);
        rental.setBook(book);
        rental.getBook().setStatus(BookStatus.RENTED);
        rental.setRentedDate(LocalDate.now());
        rental.setDueDate(LocalDate.now().plusWeeks(2));

        rental = rentalRepository.save(rental);

        return mapToCreateResponse(rental);
    }

//    @Override
//    public RentalListResponse list(boolean overdueOnly) {
//        List<RentalListResponse.Rental> list = rentalRepository.findAll()
//                .stream()
//                .filter(rental -> {
//                    if (overdueOnly) {
//                        LocalDate dueDate = LocalDate.parse(rental.getDueDate(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
//                        return dueDate.isBefore(LocalDate.now());
//                    }
//                    return true;
//                })
//                .map(this::mapToListResponse)
//                .collect(Collectors.toList());
//
//        return RentalListResponse.builder().data(list).build();
//    }

    @Override
    public RentalListResponse list(boolean overdueOnly) {
        List<RentalListResponse.Rental> list = rentalRepository.findAll()
                .stream()
                .filter(rental -> overdueOnly ? rental.getDueDate().isBefore(LocalDate.now()) : true)
                .map(this::mapToListResponse)
                .toList();

        return RentalListResponse.builder()
                .data(list)
                .build();
    }

    private RentalListResponse.Rental mapToListResponse(Rental rental){
        return RentalListResponse.Rental.builder()
                .id(rental.getId())
                .books(mapToBookListResponse(rental.getBook()))
                .user(mapToUsersListResponse(rental.getUser()))
                .rentalDate(rental.getRentedDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .dueDate(rental.getDueDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .build();
    }

    private RentalListResponse.Rental.User mapToUsersListResponse(User user){
        return RentalListResponse.Rental.User.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
    private RentalListResponse.Rental.Book mapToBookListResponse(Book book){
        return RentalListResponse.Rental.Book.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .status(book.getStatus().getTitle())
                .build();
    }

    private RentalCreateResponse mapToCreateResponse(Rental rental){
        return RentalCreateResponse.builder()
                .id(rental.getId())
                .book(mapToCreateResponse(rental.getBook()))
                .user(mapToCreateResponse(rental.getUser()))
                .rentalDate(rental.getRentedDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .dueDate(rental.getDueDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .build();
    }

    private RentalCreateResponse.User mapToCreateResponse(User user){
        return RentalCreateResponse.User.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    private RentalCreateResponse.Book mapToCreateResponse(Book book){
        return RentalCreateResponse.Book.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .status(BookStatus.RENTED)
                .build();
    }

//    private List<Book> getIfExistOrThrow(List<Long> ids){
//       return ids.stream().map(this::getIfExistBookOrThrow).toList();
//    }

    private Book getIfExistBookOrThrow(Long id){
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()){
            return optionalBook.get();
        }else {
            throw new RuntimeException();
        }
    }

    private User getIfExistOrThrow(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            return optionalUser.get();
        }else {
            throw new RuntimeException();
        }
    }

    private static Long getUserId(){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }
}
