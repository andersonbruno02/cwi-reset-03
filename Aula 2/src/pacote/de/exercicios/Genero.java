package pacote.de.exercicios;

public enum Genero {
    MASCULINO("É masculino o substantivo que admite o artigo \"o\"."),
    FEMININO("É feminino o substantivo que admite o artigo \"a\"."),
    NAO_BINARIO("O termo não-binário refere-se às pessoas que não se percebem como pertencentes a um gênero exclusivamente. Isso significa que sua identidade de gênero e expressão de gênero não são limitadas ao masculino e feminino.");

    private String descricao;

    Genero(String descricao) {
        this.descricao = descricao;
    }
}
