package com.taMagica;

public class ContaMagica implements IContaMagica {

    public static final int SILVER = 0;
    public static final int GOLD = 1;
    public static final int PLATINUM = 2;

    private double saldo;
    private int status;

    public ContaMagica() {
        this.saldo = 0.0;
        this.status = SILVER;
    }

    @Override
    public double getSaldo() {
        return this.saldo;
    }

    @Override
    public int getStatus() {
        return this.status;
    }

    @Override
    public void deposito(int valor) throws INVALID_OPER_EXCEPTION {

        if (valor < 0) {
            throw new INVALID_OPER_EXCEPTION("Valor Inválido!");
        } else {
            if (getStatus() == 0) {
                double novoSaldo = getSaldo() + valor;
                this.saldo = novoSaldo;
            } else if (getStatus() == 1) {
                double novoSaldo = getSaldo() + valor + (valor * 0.01);
                this.saldo = novoSaldo;
            } else if (getStatus() == 2) {
                double novoSaldo = getSaldo() + valor + (valor * 0.025);
                this.saldo = novoSaldo;
            }

            if (getStatus() == 0) {
                if (getSaldo() >= 50000) {
                    this.status = GOLD;
                }
            }

            else if (getStatus() == 1) {
                if (getSaldo() >= 200000) {
                    this.status = PLATINUM;
                }
            }
        }
    }

    @Override
    public void retirada(int valor) throws INVALID_OPER_EXCEPTION {

        if (valor < 0) {
            throw new INVALID_OPER_EXCEPTION("Valor Inválido!");
        } else if (getSaldo() >= valor) {
            double novoSaldo = getSaldo() - valor;
            this.saldo = novoSaldo;

            if (getStatus() == 2) {
                if (getSaldo() < 100000) {
                    this.status = GOLD;
                }
            }

            else if (getStatus() == 1) {
                if (getSaldo() < 25000) {
                    this.status = SILVER;
                }
            }

        } else {
            throw new INVALID_OPER_EXCEPTION("Saldo Insuficiente!");
        }

    }

}
