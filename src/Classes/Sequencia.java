package Classes;

import javax.swing.*;
import java.util.Random;

public class Sequencia {

    static JFrame tela = new JFrame();

    static Computador computador;

    private static int ValorCartaJogadaUser;
    private static int valorCartaJogadaComputador;


    private static int pontoRodadaUser = 0;
    private static int pontoRodadaComputador = 0;


    private static int pontosUser = 0;
    private static int pontosComputador = 0;

    private static int pontosValendo = 1;

    private static boolean telaCriada = false;


    public static int getPontosUser() {
        return pontosUser;
    }

    public static int getPontosComputador() {
        return pontosComputador;
    }


    public static Cartas[] distribuirCartas(Cartas[] cartasNoJogo) {
        Random aleatorioEscolhas = new Random();

        for (int p = 0; p < cartasNoJogo.length; p++) {
            cartasNoJogo[p] = new Cartas(aleatorioEscolhas.nextInt(9 + 1), aleatorioEscolhas.nextInt(3 + 1));
            cartasNoJogo[p].sortearNaipe(cartasNoJogo[p].getNaipeValor());
            cartasNoJogo[p].nomearImagem();
        }

        for (int p = 0; p < cartasNoJogo.length; p++) {
            for (int i = 0; i < cartasNoJogo.length; i++) {
                if (i == p) {
                    continue;
                } else if (cartasNoJogo[i].getNomeImagem().equals(cartasNoJogo[p].getNomeImagem())) {
                    cartasNoJogo[i] = new Cartas(aleatorioEscolhas.nextInt(9 + 1), aleatorioEscolhas.nextInt(3 + 1));
                    cartasNoJogo[i].sortearNaipe(cartasNoJogo[i].getNaipeValor());
                    cartasNoJogo[i].nomearImagem();
                }
            }
            if (cartasNoJogo[p].getCarta() == (cartasNoJogo[0].getCarta() + 1)) {
                cartasNoJogo[p].setCartaIndex(10);
                cartasNoJogo[p].setCarta(cartasNoJogo[p].getCarta() + cartasNoJogo[p].getNaipeValor());
                //MANILHA
            } else if (cartasNoJogo[0].getCarta() == 13 && cartasNoJogo[p].getCarta() == 4) {
                cartasNoJogo[p].setCartaIndex(10);
                cartasNoJogo[p].setCarta(cartasNoJogo[p].getCarta() + cartasNoJogo[p].getNaipeValor());
                //CASO A O VIRA SEJA O 3
            }
        }

        return cartasNoJogo;

    }

    public static void novaRodada() {

        pontosValendo = 1;
        pontoRodadaUser = 0;
        pontoRodadaComputador = 0;

        if (!telaCriada) {
            tela = Gui.criarTela();
            telaCriada = true;
        }


        Cartas[] todasAsCartas = new Cartas[7];
        Sequencia.distribuirCartas(todasAsCartas);

        Cartas vira = todasAsCartas[0];
        Cartas[] cartasMaoJogador = {todasAsCartas[1], todasAsCartas[2], todasAsCartas[5]};
        computador = new Computador(new Cartas[]{todasAsCartas[3], todasAsCartas[4], todasAsCartas[6]});

        computador.setProbalidade(computador.calculaProbabilidade());


        tela.getContentPane().removeAll();
        tela.getContentPane().revalidate();
        tela.getContentPane().repaint();
        Gui.gui(tela, cartasMaoJogador, computador, vira);


    }


    public static void rodada() {


        if (ValorCartaJogadaUser > valorCartaJogadaComputador) {
            pontoRodadaUser++;
        } else if (valorCartaJogadaComputador > ValorCartaJogadaUser) {
            pontoRodadaComputador++;
            computador.setProbalidade(computador.getProbalidade() + 0.10F);
        } else {
            pontoRodadaUser++;
            pontoRodadaComputador++;
        }

        if (pontoRodadaUser == 2) {
            JOptionPane.showMessageDialog(null, "Você Venceu a rodada!", "Vitoria!", JOptionPane.PLAIN_MESSAGE);
            pontosUser += pontosValendo;

            if (pontosUser < 12) {
                computador.setValendoTruco(false);
                novaRodada();
            } else {
                terminoPartida();
            }
        } else if (pontoRodadaComputador == 2) {
            JOptionPane.showMessageDialog(null, "Computador Venceu a rodada!", "Derrota!", JOptionPane.PLAIN_MESSAGE);
            pontosComputador += pontosValendo;

            if (pontosComputador < 12) {
                computador.setValendoTruco(false);
                novaRodada();
            } else {
                terminoPartida();
            }
        }
    }

    public static void terminoPartida() {
        if (pontosUser >= 12) {
            JOptionPane.showMessageDialog(null, "PARABENS! VOCE VENCEU!", "VITORIA!", JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(null, "DROGA! VOCE PERDEU!", "DERROTA!", JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        }
    }

    public static boolean usuarioTruco(){
        if (pontosUser == 11 || computador.isValendoTruco()){
            JOptionPane.showMessageDialog(null,"Você não pode pedir Truco!", "Ops!", JOptionPane.PLAIN_MESSAGE);
            return false;
        }else if (computador.getProbalidade() >= 0.5){
            JOptionPane.showMessageDialog(null,"Computador aceitou o Truco!", "Truco!", JOptionPane.PLAIN_MESSAGE);
            computador.setValendoTruco(true);
            pontosValendo = 3;
            return true;
        }else{
            JOptionPane.showMessageDialog(null,"Computador rejeitou o Truco!","Vitoria!", JOptionPane.PLAIN_MESSAGE);
            pontosUser ++;
            novaRodada();
            return false;
        }
    }
    public static void setValorCartaJogadaUser(int valorCartaJogadaUser) {
        Sequencia.ValorCartaJogadaUser = valorCartaJogadaUser;
    }

    public static void setValorCartaJogadaComputador(int valorCartaJogadaComputador) {
        Sequencia.valorCartaJogadaComputador = valorCartaJogadaComputador;
    }

    public static int getPontosValendo() {
        return pontosValendo;
    }

    public static void setPontosValendo(int pontosValendo) {
        Sequencia.pontosValendo = pontosValendo;
    }

    public static int getPontoRodadaUser() {
        return pontoRodadaUser;
    }

    public static void setPontoRodadaUser(int pontoRodadaUser) {
        Sequencia.pontoRodadaUser = pontoRodadaUser;
    }

    public static int getPontoRodadaComputador() {
        return pontoRodadaComputador;
    }

    public static void setPontoRodadaComputador(int pontoRodadaComputador) {
        Sequencia.pontoRodadaComputador = pontoRodadaComputador;
    }

    public static void setPontosComputador(int pontosComputador) {
        Sequencia.pontosComputador = pontosComputador;
    }
}


