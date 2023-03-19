import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.HashMap;

public class Conversor extends JFrame implements ActionListener {

	public static void main(String[] args) {
	    Conversor conversor = new Conversor();
	    conversor.mostrarJanela();
	}
	
	private JLabel label;
    private JButton buttonTemperatura, buttonMoeda;
    private DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private HashMap<String, Double> taxasDeConversao = new HashMap<>();

    public Conversor() {
        super("Conversor");

        // Definição das taxas de conversão de moedas
        taxasDeConversao.put("Reais Brasileiros para Dólar", 0.19);
        taxasDeConversao.put("Reais Brasileiros para Euro", 0.16);
        taxasDeConversao.put("Reais Brasileiros para Libras Esterlinas", 0.14);
        taxasDeConversao.put("Reais Brasileiros para Peso argentino", 19.48);
        taxasDeConversao.put("Reais Brasileiros para Peso Chileno", 144.81);
        taxasDeConversao.put("Dólar para Reais Brasileiros", 5.24);
        taxasDeConversao.put("Euro para Reais Brasileiros", 6.23);
        taxasDeConversao.put("Libras Esterlinas para Reais Brasileiros", 7.08);
        taxasDeConversao.put("Peso argentino para Reais Brasileiros", 0.05);
        taxasDeConversao.put("Peso Chileno para Reais Brasileiros", 0.007);

        // Configuração da janela
        setSize(300, 150);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Criação dos componentes
        label = new JLabel("Escolha uma opção:");
        buttonTemperatura = new JButton("Conversão de temperatura");
        buttonMoeda = new JButton("Conversão de moeda");

        // Adição dos componentes à janela
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(label);
        panel.add(buttonTemperatura);
        panel.add(buttonMoeda);
        add(panel);

        // Configuração dos listeners de eventos
        buttonTemperatura.addActionListener(this);
        buttonMoeda.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonTemperatura) {
            conversaoTemperatura();
        } else if (e.getSource() == buttonMoeda) {
            conversaoMoeda();
        }
    }

    private void conversaoTemperatura() {
        String[] opcoes = {"Celsius para Fahrenheit", "Celsius para Kelvin", "Fahrenheit para Celsius",
                "Fahrenheit para Kelvin", "Kelvin para Celsius", "Kelvin para Fahrenheit"};
        String selecao = (String) JOptionPane.showInputDialog(null, "Escolha uma opção:", "Conversão de temperatura",
                JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

        if (selecao != null) {
            String[] unidades = selecao.split(" para ");

            String entrada = JOptionPane.showInputDialog(null, "Digite o valor em " + unidades[0] + ":");
            if (entrada != null && !entrada.isEmpty()) {
                try {
                    double valorEntrada = Double.parseDouble(entrada);
                    double valorSaida = 0;

                    if (unidades[0].equals("Celsius")) {
                        if (unidades[1].equals("Fahrenheit")) {
                            valorSaida = (valorEntrada * 9 / 5) + 32;
                        } else if (unidades[1].equals("Kelvin")) {
                            valorSaida = valorEntrada + 273.15;
                        }
                    } else if (unidades[0].equals("Fahrenheit")) {
                        if (unidades[1].equals("Celsius")) {
                            valorSaida = (valorEntrada - 32) * 5 / 9;
                        } else if (unidades[1].equals("Kelvin")) {
                            valorSaida = (valorEntrada - 32) * 5 / 9 + 273.15;
                        }
                    } else if (unidades[0].equals("Kelvin")) {
                        if (unidades[1].equals("Celsius")) {
                            valorSaida = valorEntrada - 273.15;
                        } else if (unidades[1].equals("Fahrenheit")) {
                            valorSaida = (valorEntrada - 273.15) * 9 / 5 + 32;
                        }
                    }

                    JOptionPane.showMessageDialog(null, decimalFormat.format(valorEntrada) + " " + unidades[0] + " equivale a " +
                            decimalFormat.format(valorSaida) + " " + unidades[1], "Resultado", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Valor inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void conversaoMoeda() {
        String[] opcoes = {"Reais Brasileiros para Dólar", "Reais Brasileiros para Euro", "Reais Brasileiros para Libras Esterlinas",
                "Reais Brasileiros para Peso argentino", "Reais Brasileiros para Peso Chileno", "Dólar para Reais Brasileiros",
                "Euro para Reais Brasileiros", "Libras Esterlinas para Reais Brasileiros", "Peso argentino para Reais Brasileiros",
                "Peso Chileno para Reais Brasileiros"};
        String selecao = (String) JOptionPane.showInputDialog(null, "Escolha uma opção:", "Conversão de moeda",
                JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

        if (selecao != null) {
            String[] unidades = selecao.split(" para ");
            double taxaDeConversao = taxasDeConversao.get(selecao);

            String entrada = JOptionPane.showInputDialog(null, "Digite o valor em " + unidades[0] + ":");
            if (entrada != null && !entrada.isEmpty()) {
                try {
                    double valorEntrada = Double.parseDouble(entrada);
                    double valorSaida = valorEntrada * taxaDeConversao;

                    JOptionPane.showMessageDialog(null, decimalFormat.format(valorEntrada) + " " + unidades[0] + " equivale a " +
                            decimalFormat.format(valorSaida) + " " + unidades[1], "Resultado", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Valor inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public void mostrarJanela() {
        String[] opcoes = {"Conversão de temperatura", "Conversão de moeda"};
        String selecao = (String) JOptionPane.showInputDialog(null, "Escolha uma opção:", "Conversor",
                JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

        if (selecao != null) {
            if (selecao.equals("Conversão de temperatura")) {
                conversaoTemperatura();
            } else {
                conversaoMoeda();
            }
        }
    }

}


