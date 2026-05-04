package org.example;

public class LibraryControl {
    private LibraryModel bookModel;
    private NotificationService notificationService;

    public LibraryControl(LibraryModel bookModel, NotificationService notificationService) {
        this.bookModel = bookModel;
        this.notificationService = notificationService;
    }

    public boolean checkAvailability(String id) {
        return bookModel.isBookAvailable(id);
    }

    public void borrowBook(String id, String title, String username) {
        bookModel.getAllBooks();

        if (bookModel.isBookAvailable(id)) {
            bookModel.peminjaman(title, username);
            notificationService.sendNotification(username, title);
        }
    }
}