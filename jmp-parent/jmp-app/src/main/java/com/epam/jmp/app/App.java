package com.epam.jmp.app;

import com.epam.jmp.service.api.Service;
import com.epam.jmp.bank.api.service.Bank;
import com.epam.jmp.bank.api.impl.BankImpl;
import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;
import com.epam.jmp.service.exception.SubscriptionNotFoundException;
import com.epam.jmp.sevice.impl.ServiceImpl;

import java.time.LocalDate;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Service service = new ServiceImpl();
        Bank bank = new BankImpl();

        List<User> userList = service.getAllUsers();
        System.out.println(userList);

        System.out.println(service.getAverageUsersAge());

        User user = new User("Volha", "Ivanova", LocalDate.of(1986,4,15));
        BankCard bankCard = bank.createBankCard(user, BankCardType.CREDIT);

        service.subscribe(bankCard);
        Subscription subscription = service.getSubscriptionByBankCardNumber(bankCard.getNumber()).
                orElseThrow(() -> new SubscriptionNotFoundException("Subscription was not found"));
        System.out.println(subscription);
    }
}
