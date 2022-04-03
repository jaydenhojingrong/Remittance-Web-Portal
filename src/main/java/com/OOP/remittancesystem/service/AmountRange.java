package com.OOP.remittancesystem.service;

import org.springframework.stereotype.Service;

@Service
public class AmountRange implements Comparable<AmountRange> {
    long amount;
    String company;

    public AmountRange() {
    }

    public AmountRange(String company, long amount) {
        this.company = company;
        this.amount = amount;
    }

    public AmountRange(long amount) {
        this.company = "";
        this.amount = amount;
    }

    @Override
    public int compareTo(AmountRange o) {
        return amount < o.amount ? -1 : amount > o.amount ? 1 : 0;
    }

    @Override
    public String toString() {
        return "company:" + company + ", amount:" + amount;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

}
