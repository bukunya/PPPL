package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class LibraryControlTest {

    private LibraryModel bookModel;
    private NotificationService notificationService;
    private LibraryControl control;

    @BeforeEach
    void setUp() {
        bookModel = Mockito.mock(LibraryModel.class);
        notificationService = Mockito.mock(NotificationService.class);
        control = new LibraryControl(bookModel, notificationService);
    }

    @Test
    @DisplayName("Cek ketersediaan buku mengembalikan nilai dari model")
    void testCheckAvailability() {
        when(bookModel.isBookAvailable("B001")).thenReturn(true);
        when(bookModel.isBookAvailable("B002")).thenReturn(false);

        assertTrue(control.checkAvailability("B001"));
        assertFalse(control.checkAvailability("B002"));
    }

    @Test
    @DisplayName("Apabila buku tidak dipinjam (tersedia), fungsi peminjaman dipanggil")
    void testBorrowBook_WhenAvailable_ShouldCallPeminjaman() {
        when(bookModel.isBookAvailable("B001")).thenReturn(true);
        control.borrowBook("B001", "Clean Code", "Afif");
        verify(bookModel, times(1)).peminjaman("Clean Code", "Afif");
    }

    @Test
    @DisplayName("Apabila buku sedang dipinjam (tidak tersedia), fungsi peminjaman TIDAK dipanggil")
    void testBorrowBook_WhenNotAvailable_ShouldNotCallPeminjaman() {
        when(bookModel.isBookAvailable("B001")).thenReturn(false);
        control.borrowBook("B001", "Clean Code", "Afif");
        verify(bookModel, never()).peminjaman(anyString(), anyString());
    }

    @Test
    @DisplayName("Verifikasi urutan eksekusi: getAllBooks -> peminjaman -> sendNotification")
    void testBorrowBook_ExecutionOrder() {
        when(bookModel.isBookAvailable("B001")).thenReturn(true);
        control.borrowBook("B001", "Clean Code", "Afif");
        InOrder inOrder = Mockito.inOrder(bookModel, notificationService);
        inOrder.verify(bookModel).getAllBooks();
        inOrder.verify(bookModel).peminjaman("Clean Code", "Afif");
        inOrder.verify(notificationService).sendNotification("Afif", "Clean Code");
    }
}