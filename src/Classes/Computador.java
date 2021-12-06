package Classes;

import javax.swing.*;
import java.util.Random;

public class Computador {

    private boolean valendoTruco;
    private Cartas[] cartas = new Cartas[3];
    private float probalidade;


    private Random blefe = new Random();

    public Computador(Cartas[] cartas) {
        this.cartas = cartas;
    }


    public Random getBlefe() {
        return blefe;
    }

    public float /*void*/ calculaProbabilidade() {
        float probabilidade = 0.0F;

        for (int p = 0; p < cartas.length; p++) {

            switch (cartas[p].getCarta()) {
                case 8:
                    probabilidade += 0.1F;
                    break;
                case 9:
                    probabilidade += 0.15F;
                    break;
                case 10:
                    probabilidade += 0.18F;
                    break;
                case 11:
                    probabilidade += 0.2F;
                    break;
                case 12:
                    probabilidade += 0.25F;
                    break;
                case 13:
                    probabilidade += 0.3F;
                    break;
                case 14:
                    probabilidade += 0.35F;
                    break;
                case 15:
                    probabilidade += 0.4F;
                    break;
                case 16:
                    probabilidade += 0.45F;
                    break;
                case 17:
                    probabilidade += 0.5F;
                    break;
                default:
                    probabilidade += 0.05F;
                    break;
            }
        }

        return probabilidade;
    }


    public void pedirTruco() {
        int pedidoTruco = JOptionPane.showConfirmDialog(null, "Computador pediu truco? Aceita?", "TRUCO!!!",
                JOptionPane.YES_NO_OPTION);
        if (pedidoTruco == JOptionPane.YES_OPTION) {
            valendoTruco = true;
            Sequencia.setPontosValendo(3);
        } else {
            JOptionPane.showMessageDialog(null, "Computador Venceu a rodada!", "Derrota!", JOptionPane.PLAIN_MESSAGE);
            Sequencia.setPontosComputador(Sequencia.getPontosComputador() + 1);
            Sequencia.novaRodada();
        }
    }

    public Cartas[] getCartas() {
        return cartas;
    }

    public float getProbalidade() {
        return probalidade;
    }

    public void setProbalidade(float probalidade) {
        this.probalidade = probalidade;
    }

    public void setValendoTruco(boolean valendoTruco) {
        this.valendoTruco = valendoTruco;
    }

    public boolean isValendoTruco() {
        return valendoTruco;
    }

}

