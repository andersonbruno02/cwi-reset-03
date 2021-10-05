
public class Registradora {

    public static void main(String[] args) {

        //primeiroBug(); //ok

        //segundoBug(); //ok

        //terceiroBug();//ok

        //quartoBug();//ok

        //quintoBug();//ok

        sextoBug();//ok
    }

    private static double registrarItem(String item, int quantidade) {

        double precoItem = 0;
        if (ItensPorQuantidade.estoque(item, quantidade)) {
            precoItem = RelacaoPesoPreco.retornaPrecoProduto(item, quantidade);
            if (QuantidadeMinimaItem.precisaReposicao(item)) {
                if ("pão".equals(item) || "sanduíche".equals(item) || "torta".equals(item)) {
                    ReposicaoCozinha.reporItem(item);
                }
                if ("leite".equals(item) || "café".equals(item)) {
                    ReposicaoFornecedor.reporItem(item);

                }
            }
        } else {
            if ("leite".equals(item) || "café".equals(item)) {
                ReposicaoFornecedor.reporItem(item);
                precoItem = RelacaoPesoPreco.retornaPrecoProduto(item, quantidade);
            } else {
                System.out.println("Sem Estoque");
            }
        }

        return precoItem;
    }

    private static void primeiroBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        String item = "sanduíche";
        int quantidade = 4;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: R$%.2f", precoTotal));
    }

    private static void segundoBug() {
        DataProjeto.criarDataComCozinhaEncerradaMasComDiaUtil();
        String item = "torta";
        int quantidade = 10;
        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: R$%.2f", precoTotal));
    }

    private static void terceiroBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        String item = "café";
        int quantidade = 40;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));
    }

    private static void quartoBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        // Cliente 1
        String item = "sanduíche";
        int quantidade = 20;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: R$%.2f", precoTotal));

        // Cliente 2
        String item2 = "sanduíche";
        int quantidade2 = 5;

        double precoTotal2 = registrarItem(item2, quantidade2);

        System.out.println(String.format("Valor total: R$%.2f", precoTotal2));
    }

    private static void quintoBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        String item = "pão";
        int quantidade = 10;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: R$%.2f", precoTotal));
    }

    private static void sextoBug() {
        DataProjeto.criarDataComCozinhaEncerradaSemDiaUtil();
        // Cliente 1
        String item = "sanduíche";
        int quantidade = 20;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: R$%.2f", precoTotal));

        // Cliente 2
        String item2 = "sanduíche";
        int quantidade2 = 5;

        double precoTotal2 = registrarItem(item2, quantidade2);

        System.out.println(String.format("Valor total: R$%.2f", precoTotal2));
    }

}
