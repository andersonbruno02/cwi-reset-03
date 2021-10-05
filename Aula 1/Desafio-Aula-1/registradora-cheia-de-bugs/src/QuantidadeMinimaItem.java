public class QuantidadeMinimaItem {

    public static boolean precisaReposicao(String item) {
        boolean repor = false;

        if ("pão".equals(item) && ItensPorQuantidade.pao < 600) {
            repor = true;
        }

        if ("torta".equals(item) && ItensPorQuantidade.torta < 10) {
            repor = true;
        }

        if ("sanduíche".equals(item) && ItensPorQuantidade.sanduiche <= 1) {
            repor = true;
        }

        if ("café".equals(item) && ItensPorQuantidade.cafe < 12) {
            repor = true;
        }

        if ("leite".equals(item) && ItensPorQuantidade.leite < 12) {
            repor = true;
        }

        return repor;
    }
}
