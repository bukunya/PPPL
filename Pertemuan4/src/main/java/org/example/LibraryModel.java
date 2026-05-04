package org.example;

import java.util.List;

public interface LibraryModel {
    boolean isBookAvailable(String id);

    void peminjaman(String title, String username);

    List<Book> getAllBooks();
}