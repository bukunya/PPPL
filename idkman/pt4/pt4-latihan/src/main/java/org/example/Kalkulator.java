package org.example;

public class Kalkulator {
    private int a;
    private int b;

    // Konstruktor kosong untuk pengujian cekEven
    public Kalkulator() {
    }

    // Konstruktor dengan parameter untuk pengujian tambah
    public Kalkulator(int a, int b) {
        this.a = a;
        this.b = b;
    }

    // Latihan 4.3 Langkah 1: Method cekEven
    public boolean cekEven(int number) {
        return number % 2 == 0;
    }

    // Method tambah untuk menguji nilai a dan b
    public int tambah() {
        return this.a + this.b;
    }
}