package com.epam.jmp.dto;

public enum BankCardType {
    CREDIT (new CreditBankCard("", null, 0)),
    DEBIT (new DebitBankCard("", null, 0));

    private BankCard bankCard;
    BankCardType(BankCard bankCard) {
        this.bankCard = bankCard;
    }

    public BankCard getBankCard() {
        return this.bankCard;
    }
}
