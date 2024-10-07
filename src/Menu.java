import java.util.Scanner;

public class Menu {
    private final Consultas consultas;

    public Menu() {
        this.consultas = new Consultas();
    }

    public void chamaMenu() {
        Scanner leitura = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    Bem vindo(a) ao conversor de moedas
                    
                    1) Dólar para Real
                    2) Real para Dólar
                    3) Dólar para Peso Argentino
                    4) Peso Argentino para Dólar
                    5) Dólar para Euro
                    6) Euro para Dólar
                    7) Sair
                    
                    Escolha uma opção válida:
                    """);

            int resposta = leitura.nextInt();
            if (resposta == 7) {
                System.out.println("Saindo...");
                break;
            }

            System.out.println("Digite o valor a ser convertido:");
            double valor = leitura.nextDouble();

            switch (resposta) {
                case 1:
                    exibirResultado("USD", "BRL", valor);
                    break;
                case 2:
                    exibirResultado("BRL", "USD", valor);
                    break;
                case 3:
                    exibirResultado("USD", "ARS", valor);
                    break;
                case 4:
                    exibirResultado("ARS", "USD", valor);
                    break;
                case 5:
                    exibirResultado("USD", "EUR", valor);
                    break;
                case 6:
                    exibirResultado("EUR", "USD", valor);
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
        leitura.close();
    }
    private void exibirResultado(String base, String target, double valor) {
        try {
            Moedas resultado = consultas.buscaMoedas(base, target, valor);
            System.out.printf("Conversão realizada com sucesso!\nResultado: [%.2f] %s corresponde a [%.2f] %s%n",
                    valor, resultado.base_code(),
                    resultado.conversion_result(),
                    resultado.target_code()+ "\n");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
