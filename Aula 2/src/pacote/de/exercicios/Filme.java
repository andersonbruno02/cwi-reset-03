package pacote.de.exercicios;

public class Filme {
    private String nome;
    private String descricao;
    private int duracao;
    private int anoDeLancamento;
    private Diretor diretor;

    public Filme(String nome, String descricao, int duracao, int anoDeLancamento, Diretor diretor) {
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
        this.anoDeLancamento = anoDeLancamento;
        this.diretor = diretor;
    }
    public void reproduzir(){
        System.out.println("Nome do filme : " + this.nome);
        System.out.println("Descrição : " + this.descricao);
        System.out.println("Duração : " + this.duracao + " minutos");
        System.out.println("Diretor : " + diretor.getNome());
    }
}
