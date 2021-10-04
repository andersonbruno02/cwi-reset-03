public class RelacaoPesoPreco {

    public static double retornaPrecoProduto(String item, int qtd) {
        double precoTotal = 0;

        if ("pão".equals(item)) {
            precoTotal = (qtd * 60) * (12.75 / 1000);
        }

        if ("torta".equals(item)) {
            precoTotal = qtd * (96 / 16);
        }

        if ("leite".equals(item)) {
            precoTotal = 4.48 * qtd;
        }

        if ("café".equals(item)) {
            precoTotal = 9.56 * qtd;
        }

        if ("sanduíche".equals(item)) {
            precoTotal = 4.5 * qtd;
        }

        return precoTotal;
    }
}
