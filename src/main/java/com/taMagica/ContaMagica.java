package com.taMagica;

public class ContaMagica {
    public static final int SILVER = 0;
    public static final int GOLD = 1;
    public static final int PLATINUM = 2;

    private int status;
    private double saldo;

    public ContaMagica() {
        this.status = SILVER;
        this.saldo = 0.0;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public int getStatus() {
        return this.status;
    }

    public void deposito(int valor) throws INVALID_OPER_EXCEPTION {
        if (valor <= 0) {
            throw new INVALID_OPER_EXCEPTION("Não é possível depositar um valor não positivo");
        }
        this.executaDeposito(valor);
    }

    public void retirada(int valor) throws INVALID_OPER_EXCEPTION {
        if (valor <= 0){
            throw new INVALID_OPER_EXCEPTION("Não é possível retirar um valor não positivo");
        }
        this.executaRetirada(valor);
    }

    private void executaRetirada(int valor) throws INVALID_OPER_EXCEPTION {
        double novoSaldo = this.saldo - valor;
        if (novoSaldo < 0){
            throw new INVALID_OPER_EXCEPTION("Saldo não pode ficar negativo");
        }
        if (this.status == PLATINUM && novoSaldo < 100000){
            this.status = GOLD;
        }
        else if (this.status == GOLD && novoSaldo < 25000){
            this.status = SILVER;
        }
        this.saldo = novoSaldo;
    }

    private void executaDeposito(int valor) {
        double incremento = this.calculaIncremento();
        double depositoFinal = valor + valor*incremento;
        this.saldo += depositoFinal;
        if (this.status == SILVER && this.saldo >= 50000){
            this.status = GOLD;
        }
        else if(this.status == GOLD && this.saldo >= 200000){
            this.status = PLATINUM;
        }
    }

    private double calculaIncremento() {
        switch (this.status) {
            case 1:
                return 0.01;
            case 2:
                return 0.025;
            default:
                return 0;
        }
    }
}