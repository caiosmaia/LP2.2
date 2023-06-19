package Trem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.Scanner;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    private static SistemaGestao sistema;
    private static Timer timer;
    private static boolean percursoIniciado;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        sistema = new SistemaGestao();
        timer = new Timer();
        percursoIniciado = false;

        Deque<Vagao> composicao1 = sistema.getComposicao1();
        Deque<Vagao> composicao2 = sistema.getComposicao2();
        
        for (char identificador = 'A'; identificador <= 'M'; identificador++) {
            if (composicao1.size() < 13) {
                composicao1.addLast(new Vagao(identificador, identificador));
            } else if (composicao2.size() < 13) {
                composicao2.addLast(new Vagao(identificador, identificador));
            } else {
                System.out.println("As composições estão cheias. Não é possível adicionar mais vagões.");
            }
        }
        
        int opcao = 0;
        while (opcao != 7) {
            exibirMenu();
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    realizarEmbarque(scanner);
                    break;

                case 2:
                    realizarDesembarque(scanner);
                    break;

                case 3:
                    realizarTransferencia(scanner);
                    break;

                case 4:
                    listarVagoes();
                    break;

                case 5:
                    iniciarPercurso();
                    break;

                case 6:
                    encerrarPrograma();
                    break;

                default:
                    System.out.println("Opção inválida. Digite um número de 1 a 7.");
                    break;
            }

            System.out.println();
        }
    }
    
    

    private static void exibirMenu() {
        System.out.println("----- MENU -----");
        System.out.println("1. Embarcar carga");
        System.out.println("2. Desembarcar carga");
        System.out.println("3. Transferir vagão");
        System.out.println("4. Listar vagões");
        System.out.println("5. Iniciar percurso");
        System.out.println("6. Encerrar programa");
        System.out.print("Selecione uma opção: ");
    }

    private static void realizarEmbarque(Scanner scanner) {
        System.out.print("Informe a composição (1 ou 2): ");
        int composicao = scanner.nextInt();

        System.out.print("Informe a identificação do vagão (letra maiúscula): ");
        String identificacao = scanner.next();
        char identificador = Character.toUpperCase(identificacao.charAt(0));

        if (!verificarVagaoExistente(identificador)) {
            System.out.println("Vagão com identificação " + identificador + " não encontrado na composição " + composicao + ".");
            return;
        }

        System.out.print("Informe o tipo de carga: ");
        String tipoCarga = scanner.next();

        System.out.print("Informe a quantidade: ");
        int quantidade = scanner.nextInt();

        if (composicao == 1) {
            sistema.embarcarCarga1(composicao,identificador, tipoCarga, quantidade);
        } else if (composicao == 2) {
            sistema.embarcarCarga2(composicao, identificador, tipoCarga, quantidade);
        } else {
            System.out.println("Composição inválida. Selecione 1 ou 2.");
            return;
        }

        System.out.println("Carga embarcada com sucesso no vagão " + identificador + ".");
    }

    private static void realizarDesembarque(Scanner scanner) {
        System.out.print("Informe a identificação do vagão (letra maiúscula): ");
        String identificacao = scanner.next();
        char identificador = Character.toUpperCase(identificacao.charAt(0));

        if (!verificarVagaoExistente(identificador)) {
            System.out.println("Vagão com identificação " + identificador + " não encontrado.");
            return;
        }

        sistema.desembarcarCarga(identificador);
        System.out.println("Carga desembarcada com sucesso do vagão " + identificador + ".");
    }

    private static void realizarTransferencia(Scanner scanner) {
        System.out.print("Informe a identificação do vagão (letra maiúscula): ");
        String identificacao = scanner.next();
        char identificador = Character.toUpperCase(identificacao.charAt(0));

        if (!verificarVagaoExistente(identificador)) {
            System.out.println("Vagão com identificação " + identificador + " não encontrado.");
            return;
        }

        sistema.transferirVagao(identificador);
        System.out.println("Vagão " + identificador + " transferido para a outra composição.");
    }

    private static boolean verificarVagaoExistente(char identificador) {
        Deque<Vagao> composicao1 = sistema.getComposicao1();
        Deque<Vagao> composicao2 = sistema.getComposicao2();

        for (Vagao vagao : composicao1) {
            if (vagao.getIdentificador() == identificador) {
                return true;
            }
        }

        for (Vagao vagao : composicao2) {
            if (vagao.getIdentificador() == identificador) {
                return true;
            }
        }

        return false;
    }
    
    private static void listarVagoes() {
        System.out.println("Vagões na composição 1:");
        listarVagoes(sistema.getComposicao1());

        System.out.println("Vagões na composição 2:");
        listarVagoes(sistema.getComposicao2());
    }

    private static void listarVagoes(Deque<Vagao> composicao) {
        if (composicao.isEmpty()) {
            System.out.println("Nenhum vagão na composição.");
        } else {
            for (Vagao vagao : composicao) {
                System.out.println("Identificador: " + vagao.getIdentificador());
                System.out.println("Tipo de Carga: " + vagao.getTipoCarga());
                System.out.println("Quantidade: " + vagao.getQuantidade());
                System.out.println("---------------------------");
            }
        }
    }

    private static void iniciarPercurso() {
        if (sistema.getComposicao1().isEmpty() && sistema.getComposicao2().isEmpty()) {
            System.out.println("As composições estão vazias. Não é possível iniciar o percurso.");
            return;
        }

        percursoIniciado = true;
        System.out.println("Trem em movimento... Nenhuma ação pode ser feita até o fim do percurso.");

        long tempoPercurso = 10000; // Tempo em milissegundos
        sistema.setEmMovimento(true);
        sistema.setAcaoAtual(null);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (sistema.isEmMovimento()) {
                    System.out.println("O trem está em movimento...");
                } else {
                    System.out.println("O trem está parado...");
                    // Exibir ação do trem (embarcar, desembarcar, trocar de vagão)
                    if (sistema.getAcaoAtual() != null) {
                        System.out.println("Ação atual: " + sistema.getAcaoAtual());
                    }
                }
            }
        };

        timer.schedule(timerTask, tempoPercurso);
    }

    private static void encerrarPrograma() {
        timer.cancel();
        System.out.println("Programa encerrado.");
    }
}