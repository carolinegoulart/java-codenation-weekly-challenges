package challenge;

import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.ThreadLocalRandom.current;

public class Estacionamento {

    private final int TOTAL_DE_VAGAS = 10;
    private final int PONTOS_MAXIMOS_HABILITACAO = 20;
    private final int IDADE_PRIORITARIA = 55;
    private final int IDADE_MINIMA = 18;

    private List<Carro> carros_estacionados;

    public Estacionamento() {
        this.carros_estacionados = new ArrayList<>();
    }

    public void estacionar(Carro carro) {
        validaMotorista(carro);
        if (this.carros_estacionados.size() < TOTAL_DE_VAGAS) {
            this.carros_estacionados.add(carro);
        } else {
            Carro carro_nao_prioritario = buscaPrimeiroCarroNaoPrioritario();
            this.carros_estacionados.remove(carro_nao_prioritario);
            this.carros_estacionados.add(carro);
        }
    }

    private void validaMotorista(Carro carro) {
        boolean valida_idade = carro.getMotorista().getIdade() >= IDADE_MINIMA;
        boolean valida_pontos = carro.getMotorista().getPontos() <= PONTOS_MAXIMOS_HABILITACAO;
        if (!valida_idade || !valida_pontos || carro.getMotorista() == null) {
            throw new EstacionamentoException();
        }
    }

    private Carro buscaPrimeiroCarroNaoPrioritario() {
        List<Carro> carros_nao_prioritarios = new ArrayList<>();
        for (Carro carro: this.carros_estacionados) {
            if (carroNaoEhPrioritario(carro)) {
                carros_nao_prioritarios.add(carro);
            }
        }
        if (carros_nao_prioritarios.size() == 0) {
            throw new EstacionamentoException();
        }
        return carros_nao_prioritarios.get(0);
    }

    private boolean carroNaoEhPrioritario(Carro carro) {
        return carro.getMotorista().getIdade() <= IDADE_PRIORITARIA;
    }

    public int carrosEstacionados() {
        return this.carros_estacionados.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return this.carros_estacionados.contains(carro);
    }

}