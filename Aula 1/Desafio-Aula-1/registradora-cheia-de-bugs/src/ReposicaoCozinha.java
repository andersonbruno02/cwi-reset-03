public class ReposicaoCozinha {

    static void reporItem(String item) {

        if (DataProjeto.cozinhaEmFuncionamento()) {
            if ("pão".equals(item)) {
                ItensPorQuantidade.pao += 3600;
            }
            if ("torta".equals(item)) {
                ItensPorQuantidade.torta += 4 * 16;
            }
            if ("sanduíche".equals(item)) {
                ItensPorQuantidade.sanduiche += 20;
            }
        } else {
            System.out.println("Cozinha fechada!");
            ItensPorQuantidade.mostraEstoque();
        }
    }
}
