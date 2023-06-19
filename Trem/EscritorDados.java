package Trem;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class EscritorDados {

	public static void escreverDadosEmArquivo(SistemaGestao sistema) {
        String nomeArquivo = "dados.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            // Escrever as informações das composições no arquivo
            writer.write("Vagões na composição 1:\n");
            for (Vagao vagao : sistema.composicao1.getVagoes()) {
                writer.write("Identificador: " + vagao.getIdentificador() + "\n");
                writer.write("Tipo de Carga: " + vagao.getTipoCarga() + "\n");
                writer.write("Quantidade: " + vagao.getQuantidade() + "\n");
                writer.write("---------------------------\n");
            }

            writer.write("Vagões na composição 2:\n");
            for (Vagao vagao : sistema.composicao2.getVagoes()) {
                writer.write("Identificador: " + vagao.getIdentificador() + "\n");
                writer.write("Tipo de Carga: " + vagao.getTipoCarga() + "\n");
                writer.write("Quantidade: " + vagao.getQuantidade() + "\n");
                writer.write("---------------------------\n");
            }

            // Escrever as informações dos desembarques no arquivo
            writer.write("Quantidade de desembarques por tipo de commodities:\n");
            writer.write("Região R1 (Minérios): " + sistema.getDesembarquesR1() + "\n");
            writer.write("Região R2 (Grãos): " + sistema.getDesembarquesR2() + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }
	
}
