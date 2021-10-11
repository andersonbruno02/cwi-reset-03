package pacote.de.exercicios;

public class Filme {
    private String nome;
    private String descricao;
    private Integer duracao;
    private Integer anoDeLancamento;
    private Double avaliacao;
    private Diretor diretor;

    public Filme(String nome, String descricao, Integer duracao, Integer anoDeLancamento, Diretor diretor, Double avaliacao) throws AvaliacaoForaDoPadraoException {
        if (avaliacao < 1 || avaliacao > 5) {
            throw new AvaliacaoForaDoPadraoException();
        }
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
        this.anoDeLancamento = anoDeLancamento;
        this.diretor = diretor;
        this.avaliacao = avaliacao;
    }
    public void reproduzir(){
        System.out.println("***************************************************");
        System.out.println("Nome do filme : " + this.nome);
        System.out.println("Descrição : " + this.descricao);
        System.out.println("Duração : " + this.duracao + " minutos");
        System.out.println("Diretor : " + diretor.getNome());
        System.out.println("***************************************************");
    }
}
