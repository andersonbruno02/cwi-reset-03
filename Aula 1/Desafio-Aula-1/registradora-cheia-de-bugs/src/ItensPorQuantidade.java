public class ItensPorQuantidade {

    static int pao = 1000;
    static int torta = 4;
    static int sanduiche = 3;
    static int leite = 14;
    static int cafe = 14;

    static boolean estoque(String item, int qtd) {
        boolean possui = false;
        if ("pão".equals(item) && pao >= qtd * 60) {
            possui = true;
            pao = pao - (qtd * 60);
        }

        if ("torta".equals(item) && torta >= qtd) {
            possui = true;
            torta = torta - qtd;
        }

        if ("sanduíche".equals(item) && sanduiche >= qtd) {
            possui = true;
            sanduiche = sanduiche - qtd;
        }

        if ("leite".equals(item) && leite >= qtd) {
            possui = true;
            leite = leite - qtd;
        }

        if ("café".equals(item) && cafe >= qtd) {
            possui = true;
            cafe = cafe - qtd;
        }
        return possui;
    }


}
