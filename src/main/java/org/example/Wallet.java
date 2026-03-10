package org.example;

import java.util.ArrayList;
import java.util.List;

public class Wallet {
    private Owner owner;
    private List<Card> cards;
    private double cash;

    public Wallet(Owner owner) {
        this.owner = owner;
        this.cards = new ArrayList<>();
        this.cash = 0.0;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Owner getOwner() {
        return owner;
    }

    public void addCards(String bank, int nomor) {
        Card newCard = new Card(bank, nomor);
        this.cards.add(newCard);
    }

    public Card removeCard(int nomor) {
        for (Card card : cards) {
            if (card.getNomorRekening() == nomor) {
                cards.remove(card);
                return card;
            }
        }
        return null;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.cash += amount;
        }
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be > 0");
        }
        if (this.cash < amount) {
            throw new InsufficientFundsException("Insufficient funds");
        }
        this.cash -= amount;
    }

    public double getCash() {
        return cash;
    }

    public void cleanCash() {
        this.cash = 0.0;
    }

    public void cleanCards() {
        cards.clear();
    }
}