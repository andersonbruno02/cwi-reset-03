public class ItensPorQuantidade {

    static int pao = 3600;
    static int torta = 64;
    static int sanduiche = 20;
    static int leite = 20;
    static int cafe = 20;

    public static void mostraEstoque() {
        System.out.println("Pão " + getPao() + " gramas");
        System.out.println("Sanduíche " + getSanduiche() + " unidades");
        System.out.println("Torta " + getTorta() + " fatias");
        System.out.println("Leite " + getLeite() + " unidades");
        System.out.println("Café " + getCafe() + " unidades");
    }

    public static int getPao() {
        return pao;
    }

    public static int getTorta() {
        return torta;
    }

    public static int getSanduiche() {
        return sanduiche;
    }

    public static int getLeite() {
        return leite;
    }

    public static int getCafe() {
        return cafe;
    }

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
