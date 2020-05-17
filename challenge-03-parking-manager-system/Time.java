package br.com.codenation;

import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Time {

    private Long id;
    private String nome;
    private LocalDate dataCriacao;
    private String corUniformePrincipal;
    private String corUniformeSecundario;
    private Long idCapitao;
    private ValidaDados validaDados;
    private List<Jogador> jogadores = new ArrayList<>();

    public Time(Long id, String nome, LocalDate dataCriacao,
                String corUniformePrincipal, String corUniformeSecundario){
        this.validaDados = new ValidaDados();
        setId(id);
        setNome(nome);
        setDataCriacao(dataCriacao);
        setCorUniformePrincipal(corUniformePrincipal);
        setCorUniformeSecundario(corUniformeSecundario);
    }

    public void adicionarJogador(Jogador jogador) {
        this.jogadores.add(jogador);
    }

    public List<Jogador> retornarJogadores() {
        return jogadores;
    }

    public List<Long> retornarIdsJogadores() {
        return jogadores.stream()
                .map(Jogador::getId)
                .sorted()
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        validaDados.validarId(id);
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        validaDados.validaString(nome);
        this.nome = nome;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        validaDados.validaData(dataCriacao);
        this.dataCriacao = dataCriacao;
    }

    public String getCorUniformePrincipal() {
        return corUniformePrincipal;
    }

    public void setCorUniformePrincipal(String corUniformePrincipal) {
        validaDados.validaString(corUniformePrincipal);
        this.corUniformePrincipal = corUniformePrincipal;
    }

    public String getCorUniformeSecundario() {
        return corUniformeSecundario;
    }

    public void setCorUniformeSecundario(String corUniformeSecundario) {
        validaDados.validaString(corUniformeSecundario);
        this.corUniformeSecundario = corUniformeSecundario;
    }

    public void setIdCapitao(Long idCapitao) {
        validaDados.validarId(idCapitao);
        this.idCapitao = idCapitao;
    }

    public Long getIdCapitao() {
        if (this.idCapitao == null || this.idCapitao == new Long(0))
            throw new CapitaoNaoInformadoException();
        return idCapitao;
    }

}
