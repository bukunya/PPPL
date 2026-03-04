package org.example;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WalletTest {

    Wallet dompet;

    @BeforeAll
    void initClass() {
        dompet = new Wallet("Abdullah");
    }

    @AfterAll
    void cleanClass() {
        dompet = null;
    }

    @BeforeEach
    void initMethod() {
        dompet.addMoney(10000);
        dompet.addCard("DefaultBank", "000-000");
    }

    @AfterEach
    void cleanMethod() {
        dompet.cleanCards();
        dompet.cleanMoneys();
    }

    @Test
    @Order(1)
    @DisplayName("Test Inisialisasi Wallet dan Owner")
    void testIsWallet() {
        assertNotNull(dompet, "Objek dompet tidak boleh null");
        assertEquals("Abdullah", dompet.getOwner());
    }

    @Test
    @Order(2)
    @DisplayName("Test Setter Owner")
    void testOwner() {
        dompet.setOwner("Afif");
        assertEquals("Afif", dompet.getOwner());
    }

    @Test
    @DisplayName("Test Menambah dan Mengambil Kartu")
    void testCardOperations() {
        dompet.addCard("BCA", "111-222");
        dompet.addCard("Mandiri", "333-444");

        assertEquals(3, dompet.getCards().size());

        Card diambil = dompet.takeCard("111-222");

        assertAll("Verifikasi Kartu",
                () -> assertNotNull(diambil),
                () -> assertEquals("BCA", diambil.getNamaBank()),
                () -> assertEquals("111-222", diambil.getNomorRekening())
        );

        assertEquals(2, dompet.getCards().size());
    }

    @Test
    @DisplayName("Test Tambah Uang dan Hitung Total")
    void testAddMoney() {
        assertEquals(10000, dompet.calculateTotalBalance());

        dompet.addMoney(5000);
        dompet.addMoney(2000);

        assertEquals(3, dompet.getMoneys().size());
        assertIterableEquals(
                List.of(10000, 5000, 2000),
                dompet.getMoneys()
        );

        assertEquals(17000, dompet.calculateTotalBalance());
    }

    @Test
    @DisplayName("Test Mengambil Uang (Withdraw)")
    void testTakeMoney() {
        dompet.addMoney(50000);
        dompet.addMoney(20000);

        boolean isSuccess = dompet.takeMoney(20000);

        assertTrue(isSuccess, "Harusnya berhasil mengambil uang yang tersedia");
        assertEquals(60000, dompet.calculateTotalBalance(),
                "Sisa saldo harus 60.000");

        boolean isFail = dompet.takeMoney(100000);

        assertFalse(isFail, "Harusnya gagal mengambil uang yang tidak ada");
        assertEquals(60000, dompet.calculateTotalBalance(),
                "Saldo tidak boleh berubah jika gagal");
    }

    @Test
    @DisplayName("Test Mengambil Kartu Yang Tidak Ada")
    void testTakeCardNotFound() {
        Card result = dompet.takeCard("999-999");

        assertNull(result);

        assertEquals(1, dompet.getCards().size());
    }

    @Test
    @DisplayName("Test Tambah Uang Negatif")
    void testAddNegativeMoney() {
        dompet.addMoney(-5000);

        assertEquals(1, dompet.getMoneys().size());
        assertEquals(10000, dompet.calculateTotalBalance());
    }

    @Test
    @DisplayName("Test Ambil Uang Saat Kosong")
    void testTakeMoneyWhenEmpty() {
        dompet.cleanMoneys();

        boolean result = dompet.takeMoney(10000);

        assertFalse(result);
        assertEquals(0, dompet.calculateTotalBalance());
    }
}