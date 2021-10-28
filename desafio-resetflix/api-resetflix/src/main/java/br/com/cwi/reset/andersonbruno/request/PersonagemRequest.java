package br.com.cwi.reset.andersonbruno.request;

import br.com.cwi.reset.andersonbruno.domain.TipoAtuacao;

import javax.validation.constraints.NotNull;

public class PersonagemRequest {

    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo idAtor")
    private Integer idAtor;
    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo nomePersonagem")
    private String nomePersonagem;
    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo  descricaoPersonagem")
    private String descricaoPersonagem;
    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo tipoAtuacao")
    private TipoAtuacao tipoAtuacao;

    public PersonagemRequest(Integer idAtor, String nomePersonagem, String descricaoPersonagem, TipoAtuacao tipoAtuacao) {
        this.idAtor = idAtor;
        this.nomePersonagem = nomePersonagem;
        this.descricaoPersonagem = descricaoPersonagem;
        this.tipoAtuacao = tipoAtuacao;
    }

    public Integer getIdAtor() {
        return idAtor;
    }

    public String getNomePersonagem() {
        return nomePersonagem;
    }

    public String getDescricaoPersonagem() {
        return descricaoPersonagem;
    }

    public TipoAtuacao getTipoAtuacao() {
        return tipoAtuacao;
    }
}
