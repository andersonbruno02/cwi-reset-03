public class QuantidadeMinimaItem {

    public static boolean precisaReposicao(String item) {
        if ("pão".equals(item)) {
            return ItensPorQuantidade.pao < 600;
        }

        if ("torta".equals(item)) {
            return ItensPorQuantidade.torta < 10;
        }

        if ("sanduíche".equals(item)) {
            return ItensPorQuantidade.sanduiche == 1;
        }

        if ("café".equals(item)) {
            return ItensPorQuantidade.leite < 12;
        }

        if ("leite".equals(item)) {
            return ItensPorQuantidade.cafe < 12;
        }

        return false;
    }
}
