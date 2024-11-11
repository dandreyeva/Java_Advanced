package com.epam.jmp.api.service;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Service {

    void subscribe(BankCard bankCard);

    Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber);

    List<User> getAllUsers();

    double getAverageUsersAge();

    boolean isPayableUser(User user);

    List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> condition);
}
