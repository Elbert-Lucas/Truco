package Classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
public class Gui {


    public static JFrame criarTela() {
        JFrame tela = new JFrame("Truco!");
        tela.setExtendedState(JFrame.MAXIMIZED_BOTH);
        tela.setLocationRelativeTo(null);

        tela.setLayout(null);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return tela;
    }

    public static void gui(JFrame tela, Cartas[] cartasJogador, Computador computador, Cartas vira) {
        Random numerosAleatorios = new Random();
        int[] cartasJogadasComputador = new int[3];
        final int[] indiceCartaASerJogada = {0};

        //TAMANHO E LOCALIZAÇÃO


        ///

        //criando e redimensionando icon
        ImageIcon viraIcon = new ImageIcon("src\\Imagens\\" + vira.getNomeImagem());
        Image redimensionador = viraIcon.getImage().getScaledInstance(125, 225, Image.SCALE_DEFAULT);
        viraIcon = new ImageIcon(redimensionador);

        ImageIcon[] cartasJogadorIcon = new ImageIcon[3];
        for (int p = 0; p < cartasJogador.length; p++) {
            cartasJogadorIcon[p] = new ImageIcon("src\\Imagens\\" + cartasJogador[p].getNomeImagem());
            redimensionador = cartasJogadorIcon[p].getImage().getScaledInstance(150, 250, Image.SCALE_DEFAULT);
            cartasJogadorIcon[p] = new ImageIcon(redimensionador);
        }

        ImageIcon[] cartasComputadorIcon = new ImageIcon[3];
        for (int p = 0; p < computador.getCartas().length; p++) {
            cartasComputadorIcon[p] = new ImageIcon("src\\Imagens\\verso.png");
            redimensionador = cartasComputadorIcon[p].getImage().getScaledInstance(150, 250, Image.SCALE_DEFAULT);
            cartasComputadorIcon[p] = new ImageIcon(redimensionador);
        }
        ///

        //Carta na Mesa
        JLabel cartaNaMesa = new JLabel();
        cartaNaMesa.setBounds(600, 280, 150, 250);
        tela.add(cartaNaMesa);


//
//TEXTOS
        Font viraTexto = new Font("VIRA: ", Font.CENTER_BASELINE, 25);
        JLabel labelTextoVira = new JLabel("VIRA: ");
        labelTextoVira.setFont(viraTexto);
        labelTextoVira.setBounds(100, 0, 125, 225);
        tela.add(labelTextoVira);

        Font pontosTexto = new Font("PONTOS: ", Font.CENTER_BASELINE, 25);
        JLabel labelTextoPontos = new JLabel("PONTOS: ");
        labelTextoPontos.setFont(pontosTexto);
        labelTextoPontos.setBounds(1100, 200, 125, 225);
        tela.add(labelTextoPontos);

        Font fontPontosUser = new Font("PONTOS: ", Font.CENTER_BASELINE, 25);
        final JLabel[] textoPontosUser = {new JLabel("Usuario: " + String.valueOf(Sequencia.getPontosUser()))};
        textoPontosUser[0].setFont(fontPontosUser);
        textoPontosUser[0].setBounds(1090, 270, 250, 225);
        tela.add(textoPontosUser[0]);

        Font fontPontosComputador = new Font("PONTOS: ", Font.CENTER_BASELINE, 25);
        final JLabel[] textoPontosComputador = {new JLabel("Computador: " + String.valueOf(Sequencia.getPontosComputador()))};
        textoPontosComputador[0].setFont(fontPontosComputador);
        textoPontosComputador[0].setBounds(1090, 300, 250, 225);
        tela.add(textoPontosComputador[0]);




//lABEL COMPUTADOR
        int x = 800;
        int y = 0;
        JLabel[] labelCartasComputador = new JLabel[3];
        for (int p = 0; p < cartasJogadorIcon.length; p++) {
            labelCartasComputador[p] = new JLabel(cartasComputadorIcon[p]);
            labelCartasComputador[p].setBounds(x, y, 150, 250);
            labelCartasComputador[p].setName("labelCartasComputador[" + p + "]");

            tela.add(labelCartasComputador[p]);
            x += 200;
        }

        //TRUCO BUTTON
        JButton botaoTruco = new JButton("TRUCO!");
        botaoTruco.setBounds(1135, 650, 80, 50);

        botaoTruco.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Sequencia.usuarioTruco();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        tela.add(botaoTruco);
        //Listener
        MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JLabel labelListener = (JLabel) e.getSource();

                switch (labelListener.getName()) {
                    case "labelCartasJogador[0]":
                        Sequencia.setValorCartaJogadaUser(cartasJogador[0].getCarta());
                        break;
                    case "labelCartasJogador[1]":
                        Sequencia.setValorCartaJogadaUser(cartasJogador[1].getCarta());
                        break;
                    case "labelCartasJogador[2]":
                        Sequencia.setValorCartaJogadaUser(cartasJogador[2].getCarta());
                        break;
                    default:
                        assert false;

                }

                cartaNaMesa.setIcon(labelListener.getIcon());
                labelListener.hide();
                tela.remove(labelListener);



                cartasJogadasComputador[indiceCartaASerJogada[0]] = numerosAleatorios.nextInt(2 + 1);

                for (int p = 0; p < indiceCartaASerJogada[0]; p++) {

                    if (cartasJogadasComputador[indiceCartaASerJogada[0]] == cartasJogadasComputador[p]) {
                        while (cartasJogadasComputador[p] == cartasJogadasComputador[indiceCartaASerJogada[0]]) {
                            cartasJogadasComputador[indiceCartaASerJogada[0]] = numerosAleatorios.nextInt(2 + 1);
                        }
                        if (indiceCartaASerJogada[0] == 2) {
                            while (cartasJogadasComputador[indiceCartaASerJogada[0]] == cartasJogadasComputador[0] ||
                                    cartasJogadasComputador[indiceCartaASerJogada[0]] == cartasJogadasComputador[1]) {

                                cartasJogadasComputador[indiceCartaASerJogada[0]] = numerosAleatorios.nextInt(2 + 1);
                            }
                        }
                    }
                }



                JLabel cartaJogadaComputador = labelCartasComputador[cartasJogadasComputador[indiceCartaASerJogada[0]]];


                indiceCartaASerJogada[0]++;

                if ((computador.getProbalidade() > 0.5 || computador.getBlefe().nextInt(6 + 1) == 0)
                        && !computador.isValendoTruco() && Sequencia.getPontosComputador() < 11) {
                    computador.pedirTruco();

                    if (!computador.isValendoTruco()) {
                        return;
                    }
                }

                switch (cartaJogadaComputador.getName()) {
                    case "labelCartasComputador[0]":
                        cartasComputadorIcon[0] = new ImageIcon("src\\\\Imagens\\" + computador.getCartas()[0].getNomeImagem());
                        Image redimensionador = cartasComputadorIcon[0].getImage().getScaledInstance(150, 250, Image.SCALE_DEFAULT);
                        cartasComputadorIcon[0] = new ImageIcon(redimensionador);
                        cartaJogadaComputador.setIcon(cartasComputadorIcon[0]);

                        Sequencia.setValorCartaJogadaComputador(computador.getCartas()[0].getCarta());


                        labelCartasComputador[0].hide();
                        tela.remove(labelCartasComputador[0]);
                        break;

                    case "labelCartasComputador[1]":
                        cartasComputadorIcon[1] = new ImageIcon("src\\Imagens\\" + computador.getCartas()[1].getNomeImagem());
                        redimensionador = cartasComputadorIcon[1].getImage().getScaledInstance(150, 250, Image.SCALE_DEFAULT);
                        cartasComputadorIcon[1] = new ImageIcon(redimensionador);
                        cartaJogadaComputador.setIcon(cartasComputadorIcon[1]);

                        Sequencia.setValorCartaJogadaComputador(computador.getCartas()[1].getCarta());

                        labelCartasComputador[1].hide();
                        tela.remove(labelCartasComputador[1]);
                        break;

                    case "labelCartasComputador[2]":
                        cartasComputadorIcon[2] = new ImageIcon("src\\Imagens\\"+ computador.getCartas()[2].getNomeImagem());
                        redimensionador = cartasComputadorIcon[2].getImage().getScaledInstance(150, 250, Image.SCALE_DEFAULT);
                        cartasComputadorIcon[2] = new ImageIcon(redimensionador);
                        cartaJogadaComputador.setIcon(cartasComputadorIcon[2]);

                        Sequencia.setValorCartaJogadaComputador(computador.getCartas()[2].getCarta());

                        labelCartasComputador[2].hide();
                        tela.remove(labelCartasComputador[2]);
                        break;

                    default:
                        assert false;

                }

                cartaNaMesa.setIcon(cartaJogadaComputador.getIcon());


                Sequencia.rodada();

                computador.setProbalidade(computador.getProbalidade() + 0.05F);


                textoPontosUser[0].setText("Usuario: " + String.valueOf(Sequencia.getPontosUser()));

                textoPontosComputador[0].setText("Computador: " + String.valueOf(Sequencia.getPontosComputador()));


            }


            @Override
            public void mousePressed(MouseEvent e) {


            }


            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                JLabel label = (JLabel) e.getSource();
                label.setSize(175, 275);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JLabel label = (JLabel) e.getSource();
                label.setSize(150, 250);


            }
        };

        //labels dos icons das cartas

        JLabel labelvira = new JLabel(viraIcon);
        labelvira.setBounds(100, 150, 125, 225);
        tela.add(labelvira);

        y = 550;
        x = 150;
        JLabel[] labelCartasJogador = new JLabel[3];
        for (int p = 0; p < cartasJogadorIcon.length; p++) {
            labelCartasJogador[p] = new JLabel(cartasJogadorIcon[p]);
            labelCartasJogador[p].setBounds(x, y, 150, 250);
            labelCartasJogador[p].setCursor(new Cursor(Cursor.HAND_CURSOR));
            labelCartasJogador[p].setName("labelCartasJogador[" + p + "]");

            tela.add(labelCartasJogador[p]);
            x += 200;
        }


        labelCartasJogador[2].addMouseListener(mouseListener);
        labelCartasJogador[1].addMouseListener(mouseListener);
        labelCartasJogador[0].addMouseListener(mouseListener);
        ///
        tela.setVisible(true);
    }

}


