package org.example;

import java.util.ArrayList;
import java.util.List;

public class Wallet {
    private String owner;
    private List<Card> cards;
    private List<Integer> moneys;

    public Wallet(String owner) {
        this.owner = owner;
        this.cards = new ArrayList<>();
        this.moneys = new ArrayList<>();
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void addCard(String bank, String nomor) {
        Card newCard = new Card(bank, nomor);
        this.cards.add(newCard);
    }

    public Card takeCard(String nomor) {
        for (Card card : cards) {
            if (card.getNomorRekening().equals(nomor)) {
                cards.remove(card);
                return card;
            }
        }
        return null;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addMoney(Integer lembaran) {
        if (lembaran > 0) {
            this.moneys.add(lembaran);
        }
    }

    public boolean takeMoney(Integer lembaran) {
        if (this.moneys.contains(lembaran)) {
            this.moneys.remove(lembaran);
            return true;
        }
        return false;
    }

    public int calculateTotalBalance() {
        int total = 0;
        for (Integer uang : this.moneys) {
            total += uang;
        }
        return total;
    }

    public List<Integer> getMoneys() {
        return moneys;
    }
}