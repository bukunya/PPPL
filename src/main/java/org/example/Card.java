package org.example;

public class Card {
    private String namaBank;
    private int nomorRekening;

    public Card(String namaBank, int nomorRekening) {
        this.namaBank = namaBank;
        this.nomorRekening = nomorRekening;
    }

    public String getNamaBank() {
        return namaBank;
    }

    public int getNomorRekening() {
        return nomorRekening;
    }
}