//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Classes;

public class Cartas {
    private int[] carta = new int[]{4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
    private int cartaIndex;
    private int naipeValor;
    String nomeImagem;
    private Naipes naipe;

    public Cartas() {
    }

    public Cartas(int var1, int var2) {
        this.cartaIndex = var1;
        this.naipeValor = var2;
    }

    public void sortearNaipe(int var1) {
        switch(var1) {
            case 0:
                this.naipe = Naipes.OUROS;
                break;
            case 1:
                this.naipe = Naipes.ESPADA;
                break;
            case 2:
                this.naipe = Naipes.COPAS;
                break;
            case 3:
                this.naipe = Naipes.PAUS;
                break;
            default:
                assert false;
        }

    }

    public void nomearImagem() {
        String var10001 = this.getNaipe().getNomeNaipe();
        this.nomeImagem = var10001 + this.getCarta() + ".png";
    }

    public void setCartaIndex(int var1) {
        this.cartaIndex = var1;
    }

    public void setCarta(int var1) {
        this.carta[10] = var1;
    }

    public int getCartaIndex() {
        return this.cartaIndex;
    }

    public int getNaipeValor() {
        return this.naipeValor;
    }

    public int getCarta() {
        return this.carta[this.cartaIndex];
    }

    public Naipes getNaipe() {
        return this.naipe;
    }

    public String getNomeImagem() {
        return this.nomeImagem;
    }
}
