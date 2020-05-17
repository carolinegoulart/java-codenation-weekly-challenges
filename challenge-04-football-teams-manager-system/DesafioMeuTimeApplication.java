package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

import static java.util.Collections.reverseOrder;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	private List<Time> times;

	public DesafioMeuTimeApplication() {
		this.times = new ArrayList<>();
	}

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao,
							String corUniformePrincipal, String corUniformeSecundario) {
		verificarTimeIdNoCadastro(id);
		Time time = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
		this.times.add(time);
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento,
							   Integer nivelHabilidade, BigDecimal salario) {
		verificarJogadorIdNoCadastro(id);
		Time time = buscarTimeNoCadastro(idTime);
		Jogador jogador = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
		time.adicionarJogador(jogador);
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		Jogador jogador = buscarJogadorNoCadastro(idJogador);
		Time time = buscarTimeNoCadastro(jogador.getIdTime());
		time.setIdCapitao(idJogador);
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		Time time = buscarTimeNoCadastro(idTime);
		return time.getIdCapitao();
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		Jogador jogador = buscarJogadorNoCadastro(idJogador);
		return jogador.getNome();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		Time time = buscarTimeNoCadastro(idTime);
		return time.getNome();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		return buscarTimeNoCadastro(idTime)
				.retornarJogadores()
				.stream()
				.map(Jogador::getId)
				.sorted()
				.collect(Collectors.toList());
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		        return buscarTimeNoCadastro(idTime)
                .retornarJogadores()
                .stream()
                .sorted(Comparator.comparing(Jogador::getNivelHabilidade).reversed()
                    .thenComparing(Jogador::getId))
                .findFirst()
				.get().getId();
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		return buscarTimeNoCadastro(idTime)
				.retornarJogadores()
				.stream()
				.sorted(Comparator.comparing(Jogador::getDataNascimento)
						.thenComparing(Jogador::getId))
				.findFirst()
				.get().getId();
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		return this.times.stream()
				.map(Time::getId)
				.sorted()
				.collect(Collectors.toList());
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		return buscarTimeNoCadastro(idTime)
				.retornarJogadores()
				.stream()
				.sorted(Comparator.comparing(Jogador::getSalario).reversed()
						.thenComparing(Jogador::getId))
				.findFirst()
				.get().getId();
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		return buscarJogadorNoCadastro(idJogador).getSalario();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		List<Jogador> todosJogadores = new ArrayList<>();
		times.forEach(time -> todosJogadores.addAll(time.retornarJogadores()));
		todosJogadores.sort(Comparator.comparing(Jogador::getNivelHabilidade, reverseOrder())
				.thenComparingLong(Jogador::getId));
		return todosJogadores.stream()
				.limit(top)
				.map(Jogador::getId)
				.collect(Collectors.toList());
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		String corCamisaTimeDeDentro = buscarTimeNoCadastro(timeDaCasa).getCorUniformePrincipal();
		String corCamisaTimeDeFora = buscarTimeNoCadastro(timeDeFora).getCorUniformePrincipal();
		if (corCamisaTimeDeDentro.equals(corCamisaTimeDeFora)) {
			return buscarTimeNoCadastro(timeDeFora).getCorUniformeSecundario();
		}
		return corCamisaTimeDeFora;
	}

	private void verificarTimeIdNoCadastro(Long id) {
		for (Time time_cadastrado: this.times) {
			if (time_cadastrado.getId().equals(id))
				throw new IdentificadorUtilizadoException();
		}
	}

	private Time buscarTimeNoCadastro(Long idTime) {
		for (Time time_cadastrado: this.times) {
			if (time_cadastrado.getId().equals(idTime))
				return time_cadastrado;
		}
		throw new TimeNaoEncontradoException();
	}

	private void verificarJogadorIdNoCadastro(Long id) {
		for (Time time_cadastrado: this.times) {
			List<Long> jogadoresId = time_cadastrado.retornarIdsJogadores();
			for (Long jogadorId: jogadoresId) {
				if (jogadorId.equals(id))
					throw new IdentificadorUtilizadoException();
			}
		}
	}

	private Jogador buscarJogadorNoCadastro(Long id) {
		for (Time time_cadastrado: this.times) {
			List<Jogador> jogadores = time_cadastrado.retornarJogadores();
			for (Jogador jogador_cadastrado: jogadores) {
				if (jogador_cadastrado.getId().equals(id))
					return jogador_cadastrado;
			}
		}
		throw new JogadorNaoEncontradoException();
	}

}
