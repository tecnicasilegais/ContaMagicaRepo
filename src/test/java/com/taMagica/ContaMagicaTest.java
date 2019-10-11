package com.taMagica;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ContaMagicaTest {
    private ContaMagica conta;
    private int INICIAL_GOLD = 50000;
    private int INICIAL_PLATINUM = 200000;

    @BeforeEach
    public void setup() {
        conta = new ContaMagica();
    }

    @Test
    @Tag("status")
    public void StatusNovaSilver() {
        assertEquals(0, conta.getStatus());
    }

    @ParameterizedTest
    @CsvSource({ "50000", "50001", "199999", "200000" })
    @Tag("status")
    @Tag("upgrade")
    public void StatusUpgradeParaGold(int valorDeposito) throws INVALID_OPER_EXCEPTION {
        conta.deposito(valorDeposito);
        assertEquals(1, conta.getStatus());
    }

    @Test
    @Tag("status")
    @Tag("upgrade")
    public void StatusUpgradeParaPlatinum() throws INVALID_OPER_EXCEPTION {
        conta.deposito(100000);
        conta.deposito(100000);
        assertEquals(2, conta.getStatus());
    }

    @Test
    @Tag("status")
    @Tag("downgrade")
    public void StatusDowngradeParaSilver() throws INVALID_OPER_EXCEPTION {
        conta.deposito(INICIAL_GOLD);
        conta.retirada((int) conta.getSaldo());
        assertEquals(0, conta.getStatus());
    }

    @Test
    @Tag("status")
    @Tag("downgrade")
    public void StatusDowngradeParaGold() throws INVALID_OPER_EXCEPTION {
        conta.deposito(INICIAL_GOLD);
        conta.deposito(INICIAL_PLATINUM);
        conta.retirada(INICIAL_PLATINUM);
        assertEquals(1, conta.getStatus());
    }

    @Test
    @Tag("status")
    @Tag("downgrade")
    public void StatusDowngradePlatinumParaSilver() throws INVALID_OPER_EXCEPTION {
        conta.deposito(INICIAL_GOLD);
        conta.deposito(INICIAL_PLATINUM);
        conta.retirada((int) conta.getSaldo());
        assertEquals(1, conta.getStatus());
    }

    @ParameterizedTest
    @CsvSource({ "1", "25000", "49999", "50000", "200000" })
    @Tag("saldo")
    @Tag("deposito")
    public void SaldoDepositoSilver(int valorDeposito) throws INVALID_OPER_EXCEPTION {
        conta.deposito(valorDeposito);
        assertEquals(valorDeposito, conta.getSaldo());
    }

    @ParameterizedTest
    @CsvSource({ "1", "25000", "49999", "50000", "200000" })
    @Tag("saldo")
    @Tag("deposito")
    public void SaldoDepositoGold(int valorDeposito) throws INVALID_OPER_EXCEPTION {
        conta.deposito(INICIAL_GOLD);
        conta.deposito(valorDeposito);
        assertEquals(INICIAL_GOLD + (valorDeposito * 1.01), conta.getSaldo());
    }

    @ParameterizedTest
    @CsvSource({ "1", "25000", "49999", "50000", "200000" })
    @Tag("saldo")
    @Tag("deposito")
    public void SaldoDepositoPlatinum(int valorDeposito) throws INVALID_OPER_EXCEPTION {
        conta.deposito(INICIAL_GOLD);
        conta.deposito(INICIAL_PLATINUM);
        conta.deposito(valorDeposito);
        assertEquals(INICIAL_GOLD + (INICIAL_PLATINUM * 1.01) + (valorDeposito * 1.025), conta.getSaldo());
    }

    @ParameterizedTest
    @CsvSource({ "1", "25000", "49999", "50000", "200000" })
    @Tag("saldo")
    @Tag("retirada")
    public void SaldoRetiradaSilver(int valorDeposito) throws INVALID_OPER_EXCEPTION {
        conta.deposito(valorDeposito);
        conta.retirada(valorDeposito);
        assertEquals(0, conta.getSaldo(), 0.001);
    }

    @ParameterizedTest
    @CsvSource({ "1", "25000", "49999", "50000", "200000" })
    @Tag("saldo")
    @Tag("retirada")
    public void SaldoRetiradaGold(int valorDeposito) throws INVALID_OPER_EXCEPTION {
        conta.deposito(INICIAL_GOLD);
        conta.deposito(valorDeposito);
        conta.retirada(valorDeposito);
        assertEquals(INICIAL_GOLD + (valorDeposito * 0.01), conta.getSaldo(), 0.001);
    }

    @ParameterizedTest
    @CsvSource({ "1", "25000", "49999", "50000", "200000" })
    @Tag("saldo")
    @Tag("retirada")
    public void SaldoRetiradaPlatinum(int valorDeposito) throws INVALID_OPER_EXCEPTION {
        conta.deposito(INICIAL_GOLD);
        conta.deposito(INICIAL_PLATINUM);
        conta.deposito(valorDeposito);
        conta.retirada(valorDeposito);
        assertEquals(INICIAL_GOLD + (INICIAL_PLATINUM * 1.01) + (valorDeposito * 0.025), conta.getSaldo(), 0.001);
    }

    @ParameterizedTest
    @CsvSource({ "1", "25000", "49999", "50000", "200000" })
    @Tag("saldo")
    @Tag("retirada")
    public void SaldoRetiradaNegativoSilver(int valorDeposito) throws INVALID_OPER_EXCEPTION {
        conta.deposito(valorDeposito);
        assertThrows(INVALID_OPER_EXCEPTION.class, () -> conta.retirada((int) conta.getSaldo() + valorDeposito));
    }

    @ParameterizedTest
    @CsvSource({ "1", "25000", "49999", "50000", "200000" })
    @Tag("saldo")
    @Tag("retirada")
    public void SaldoRetiradaNegativoGold(int valorDeposito) throws INVALID_OPER_EXCEPTION {
        conta.deposito(INICIAL_GOLD);
        conta.deposito(valorDeposito);
        assertThrows(INVALID_OPER_EXCEPTION.class, () -> conta.retirada((int) conta.getSaldo() + valorDeposito));
    }

    @ParameterizedTest
    @CsvSource({ "1", "25000", "49999", "50000", "200000" })
    @Tag("saldo")
    @Tag("retirada")
    public void SaldoRetiradaNegativoPlatinum(int valorDeposito) throws INVALID_OPER_EXCEPTION {
        conta.deposito(INICIAL_GOLD);
        conta.deposito(INICIAL_PLATINUM);
        conta.deposito(valorDeposito);
        assertThrows(INVALID_OPER_EXCEPTION.class, () -> conta.retirada((int) conta.getSaldo() + valorDeposito));
    }
}