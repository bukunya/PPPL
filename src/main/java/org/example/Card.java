package org.example;

public class Card {
    private String namaBank;
    private String nomorRekening;

    public Card(String namaBank, String nomorRekening) {
        this.namaBank = namaBank;
        this.nomorRekening = nomorRekening;
    }

    public String getNamaBank() {
        return namaBank;
    }

    public String getNomorRekening() {
        return nomorRekening;
    }
}