package com.taMagica;

public interface IContaMagica {
    public static final int SILVER = 0;
    public static final int GOLD = 1;
    public static final int PLATINUM = 2;

    double getSaldo();

    int getStatus();

    void deposito(int valor) throws INVALID_OPER_EXCEPTION;

    void retirada(int valor) throws INVALID_OPER_EXCEPTION;
}