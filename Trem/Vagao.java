package Trem;
import java.util.*;
public class Vagao {
	
	    private char identificador;
	    private String tipoCarga;
	    private int quantidade;
	    private int composicao;

	    public Vagao(char identificador, int composicao) {
	        this.identificador = identificador;
	        this.composicao = composicao;
	    }

	    public char getIdentificador() {
	        return identificador;
	    }

	    public int getComposicao() {
	        return composicao;
	    }

	    public String getTipoCarga() {
	        return tipoCarga;
	    }

	    public void setTipoCarga(String tipoCarga) {
	        this.tipoCarga = tipoCarga;
	    }

	    public int getQuantidade() {
	        return quantidade;
	    }

	    public void setQuantidade(int quantidade) {
	        this.quantidade = quantidade;
	    }
	}
