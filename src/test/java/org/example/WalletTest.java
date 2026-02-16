package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class WalletTest {

    @Test
    @DisplayName("Test Inisialisasi Wallet dan Owner")
    void testWalletOwner() {
        Wallet dompet = new Wallet("Budi");
        assertNotNull(dompet, "Objek dompet tidak boleh null");
        assertEquals("Budi", dompet.getOwner());
        dompet.setOwner("Andi");
        assertEquals("Andi", dompet.getOwner());
    }

    @Test
    @DisplayName("Test Menambah dan Mengambil Kartu")
    void testCardOperations() {
        Wallet dompet = new Wallet("Siti");

        dompet.addCard("BCA", "111-222");
        dompet.addCard("Mandiri", "333-444");
        assertEquals(2, dompet.getCards().size());
        Card diambil = dompet.takeCard("111-222");

        assertAll("Verifikasi Kartu",
                () -> assertNotNull(diambil),
                () -> assertEquals("BCA", diambil.getNamaBank()),
                () -> assertEquals("111-222", diambil.getNomorRekening())
        );

        assertEquals(1, dompet.getCards().size());
    }

    @Test
    @DisplayName("Test Tambah Uang dan Hitung Total")
    void testAddMoney() {
        Wallet dompet = new Wallet("Joko");
        assertEquals(0, dompet.calculateTotalBalance());

        dompet.addMoney(10000);
        dompet.addMoney(5000);
        dompet.addMoney(500);

        assertEquals(15500, dompet.calculateTotalBalance());
    }

    @Test
    @DisplayName("Test Mengambil Uang (Withdraw)")
    void testTakeMoney() {
        Wallet dompet = new Wallet("Lina");
        dompet.addMoney(50000);
        dompet.addMoney(20000);

        boolean isSuccess = dompet.takeMoney(20000);

        assertTrue(isSuccess, "Harusnya berhasil mengambil uang yang tersedia");
        assertEquals(50000, dompet.calculateTotalBalance(), "Sisa saldo harus 50.000");

        boolean isFail = dompet.takeMoney(100000);

        assertFalse(isFail, "Harusnya gagal mengambil uang yang tidak ada");
        assertEquals(50000, dompet.calculateTotalBalance(), "Saldo tidak boleh berubah jika gagal");
    }
}