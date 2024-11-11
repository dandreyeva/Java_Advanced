package com.epam.jmp.service.impl;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;
import com.epam.jmp.api.service.Service;
import com.epam.jmp.service.db.ConnectionToDB;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class ServiceImpl implements Service {

    @Override
    public void subscribe(BankCard bankCard) {
        try (var connection = ConnectionToDB.getConnection();
             var preparedStatement = connection.prepareStatement
                     ("INSERT INTO bank_schema.subscription (bankcard, startdate) VALUES (?, ?)")) {
            preparedStatement.setString(1, bankCard.getNumber());
            preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
            preparedStatement.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber) {
        Optional<Subscription> optionalSubscription = Optional.empty();
        try (var connection = ConnectionToDB.getConnection();
             var preparedStatement = connection.prepareStatement
                     ("SELECT*FROM bank_schema.subscription WHERE bankcard = ?")) {
            preparedStatement.setString(1, cardNumber);
            var results = preparedStatement.executeQuery();

            while (results.next()) {
                var card = results.getString(2);
                var startDate = results.getDate(3).toLocalDate();
                optionalSubscription = Optional.of(new Subscription(card, startDate));
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return optionalSubscription;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (var connection = ConnectionToDB.getConnection();
             var statement = connection.createStatement()) {
            var results = statement.executeQuery("SELECT*FROM bank_schema.user");

            while (results.next()) {
                var name = results.getString(2);
                var surName = results.getString(3);
                var birthDate = results.getDate(4).toLocalDate();
                var user = new User(name, surName, birthDate);
                userList.add(user);
            }
            } catch(SQLException e){
                throw new RuntimeException(e);
            }
            return userList;
        }

    @Override
    public double getAverageUsersAge() {
        List<User> userList = getAllUsers();
        return  userList.stream().
                map(User::getBirthday).
                mapToDouble(birthDate -> ChronoUnit.YEARS.between(birthDate, LocalDate.now()))
                               .average().getAsDouble();
    }

    @Override
    public boolean isPayableUser(User user) {
        return ChronoUnit.YEARS.between(user.getBirthday(), LocalDate.now()) >= 18;
    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> condition) {
        List<Subscription> subscriptionList = new ArrayList<>();
        try (var connection = ConnectionToDB.getConnection();
             var statement = connection.createStatement()) {
            var results = statement.executeQuery("SELECT*FROM bank_schema.subscription");

            while (results.next()) {
                var card = results.getString(2);
                var startDate = results.getDate(3).toLocalDate();
                var subscription = new Subscription(card, startDate);
                subscriptionList.add(subscription);
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return subscriptionList.stream().filter(condition).collect(Collectors.toList());
    }
}
