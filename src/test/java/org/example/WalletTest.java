package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WalletTest {

    Wallet dompet;

    @BeforeAll
    void initClass() {
        Owner defaultOwner = new Owner("1", "Abdullah", "abd@ugm.ac.id");
        dompet = new Wallet(defaultOwner);
    }

    @AfterAll
    void cleanClass() {
        dompet = null;
    }

    @BeforeEach
    void initMethod() {
        dompet.deposit(10000.0);
        dompet.addCards("DefaultBank", 0);
    }

    @AfterEach
    void cleanMethod() {
        dompet.cleanCards();
        dompet.cleanCash();
    }

    @Test
    @Order(1)
    @DisplayName("Execute constructor, verify fields")
    void testIsWallet() {
        assertNotNull(dompet, "Objek dompet tidak boleh null");
        assertEquals("Abdullah", dompet.getOwner().getName());
    }

    @ParameterizedTest
    @ValueSource(doubles = {10000.0, 50000.0, 100000.0})
    @DisplayName("Tugas 1: Test Nominal Cash Valid (Deposit)")
    void testCashValid(double nominal) {
        dompet.cleanCash();
        dompet.deposit(nominal);
        assertEquals(nominal, dompet.getCash());
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1000.0, -5000.0, 0.0})
    @DisplayName("Tugas 1: Test Nominal Cash Tidak Valid (Deposit diabaikan)")
    void testCashInvalid(double nominal) {
        dompet.cleanCash();
        dompet.deposit(nominal);
        assertEquals(0.0, dompet.getCash()); // Saldo tetap 0
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/valid-withdraw.csv", numLinesToSkip = 1)
    @DisplayName("Tugas 2: Test Withdraw Valid (CSV)")
    void testValidWithdrawCSV(double deposit, double withdraw, double expectedTotal) throws InsufficientFundsException {
        dompet.cleanCash();
        dompet.deposit(deposit);

        if (withdraw > 0) {
            dompet.withdraw(withdraw);
        }

        assertEquals(expectedTotal, dompet.getCash());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/invalid-withdraw.csv", numLinesToSkip = 1)
    @DisplayName("Tugas 2: Test Withdraw Invalid Exception (CSV)")
    void testInvalidWithdrawCSV(double deposit, double withdraw, String exceptionType) {
        dompet.cleanCash();
        dompet.deposit(deposit);

        if (exceptionType.equals("InsufficientFundsException")) {
            assertThrows(InsufficientFundsException.class, () -> dompet.withdraw(withdraw));
        } else if (exceptionType.equals("IllegalArgumentException")) {
            assertThrows(IllegalArgumentException.class, () -> dompet.withdraw(withdraw));
        }
    }

    static Stream<Arguments> provideOwnerObjects() {
        return Stream.of(
                Arguments.of(new Owner("1", "Agus", "agus@ugm.ac.id")),
                Arguments.of(new Owner("2", "Siti", "siti@ugm.ac.id"))
        );
    }

    @ParameterizedTest
    @MethodSource("provideOwnerObjects")
    @DisplayName("Tugas 3: Test Set Owner Menggunakan Object")
    void testSetOwnerObject(Owner ownerParam) {
        dompet.setOwner(ownerParam);
        assertNotNull(dompet.getOwner());
        assertEquals(ownerParam.getName(), dompet.getOwner().getName());
        assertEquals(ownerParam.getEmail(), dompet.getOwner().getEmail());
    }
}