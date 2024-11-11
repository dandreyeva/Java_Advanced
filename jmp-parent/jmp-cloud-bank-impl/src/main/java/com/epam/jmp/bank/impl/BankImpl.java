package com.epam.jmp.bank.impl;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.User;
import com.epam.jmp.bank.api.service.Bank;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class BankImpl implements Bank {
    @Override
    public BankCard createBankCard(User user, BankCardType cardType) {
        BankCard bankCard = cardType.getBankCard();
        bankCard.setUser(user);
        bankCard.setNumber(createCardNumber());
        System.out.println(bankCard.getNumber());
        return bankCard;
    }

    private String createCardNumber() {
        List<Integer> intList = List.of(ThreadLocalRandom.current().nextInt(1000, 9999),
                ThreadLocalRandom.current().nextInt(1000, 9999),
                ThreadLocalRandom.current().nextInt(1000, 9999),
                ThreadLocalRandom.current().nextInt(1000, 9999));
        return intList.stream().map(e -> e.toString()).reduce("", String::concat);
    }
}
