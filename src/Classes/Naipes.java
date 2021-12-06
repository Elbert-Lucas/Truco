package Classes;

public enum Naipes {
    PAUS(3, "paus"),
    COPAS(2, "copas"),
    ESPADA(1, "espadas"),
    OUROS(0, "ouros");

    private int valorNaipe;
    private String nomeNaipe;

    Naipes(int valorNaipe, String nomeNaipe) {
        this.valorNaipe = valorNaipe;
        this.nomeNaipe = nomeNaipe;
    }

    public int getValorNaipe() {
        return valorNaipe;
    }

    public String getNomeNaipe() {
        return nomeNaipe;
    }
}
