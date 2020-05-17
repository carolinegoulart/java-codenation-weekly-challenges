package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ValidaDados {

    public void validarId(Long id) {
        if (id == null)
            throw new NullPointerException();
        if (id < 0)
            throw new IllegalArgumentException();
    }

    public void validaString(String string) {
        if(string == null || string.trim().isEmpty())
            throw new NullPointerException();
    }

    public void validaData(LocalDate data) {
        if(data == null) {
            throw new NullPointerException();
        }
    }

    public void validaHabilidade(Integer nivelHabilidade) {
        if (nivelHabilidade == null)
            throw new NullPointerException();
        if (nivelHabilidade < 0 || nivelHabilidade > 100)
            throw new IllegalArgumentException();
    }

    public void validaSalario (BigDecimal salario) {
        if (salario == null)
            throw new NullPointerException();
        if (salario.compareTo(new BigDecimal(0)) < 0)
            throw new IllegalArgumentException();
    }

}
