package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KalkulatorTest {

    // Latihan 4.3 Langkah 2 & 3: Menggunakan @ValueSource
    // Cocok untuk parameter tunggal (single-value parameter)
    @ParameterizedTest(name = "Eksekusi ke-{index}, nilai: {arguments}")
    @ValueSource(ints = {2, 4, 6})
    void cekEven(int param) {
        Kalkulator kalk = new Kalkulator();
        assertTrue(kalk.cekEven(param));
    }

    // Latihan 4.3 Langkah 4: Menggunakan @CsvSource
    // Cocok untuk pengujian yang butuh banyak parameter (input a, input b, expected)
    @ParameterizedTest
    @CsvSource({
            "2, 4, 6",      // a=2, b=4, expect=6
            "1, 2, 3",      // a=1, b=2, expect=3
            "100, 500, 600" // a=100, b=500, expect=600
    })
    void tambahParameterized(int a, int b, int expect) {
        Kalkulator kalk = new Kalkulator(a, b);
        int actual = kalk.tambah();
        assertEquals(expect, actual);
    }

    // Latihan 4.3 Langkah 5: Membuat Method Penyedia Stream<Arguments>
    // Ini adalah data source untuk @MethodSource di bawah
    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.of(2, 4, 6),
                Arguments.of(1, 2, 3),
                Arguments.of(100, 500, 600)
        );
    }

    // Latihan 4.3 Langkah 6: Menggunakan @MethodSource
    // Memanggil nama method provider sebagai sumber data (provideParameters)
    @ParameterizedTest
    @MethodSource("provideParameters")
    void tambahparam(int a, int b, int expect) {
        Kalkulator kalk = new Kalkulator(a, b);
        int actual = kalk.tambah();
        assertEquals(expect, actual);
    }
}