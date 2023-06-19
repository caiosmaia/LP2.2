package Trem;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class SistemaGestao {

	    private Deque<Vagao> composicao1;
	    private Deque<Vagao> composicao2;
	    private boolean emMovimento;
	    private String acaoAtual;

	    public SistemaGestao() {
	    	composicao1 = new ArrayDeque<>();
	        composicao2 = new ArrayDeque<>();
	        emMovimento = false;
	        acaoAtual = null;
	        
	        for (char c = 'A'; c <= 'M'; c++) {
	            Vagao vagao1 = new Vagao(c, c);
	            composicao1.add(vagao1);
	        }

	        // Adicionar vagões à composição 2
	        for (char c = 'A'; c <= 'M'; c++) {
	            Vagao vagao2 = new Vagao(c, c);
	            composicao2.add(vagao2);
	        }
	    }
	    
	    public void embarcarCarga1(int composicao, char identificador, String tipoCarga, int quantidade) {
	        Vagao vagao1 = encontrarVagao(identificador);
	        if (vagao1== null) {
	            System.out.println("Vagão com identificador " + identificador + " não encontrado.");
	            return;
	        }

	        vagao1.setTipoCarga(tipoCarga);
	        vagao1.setQuantidade(quantidade);
	    }
	    
	    public void embarcarCarga2(int composicao, char identificador, String tipoCarga, int quantidade) {
	        Vagao vagao2 = encontrarVagao(identificador);
	        if (vagao2 == null) {
	            System.out.println("Vagão com identificador " + identificador + " não encontrado.");
	            return;
	        }

	        vagao2.setTipoCarga(tipoCarga);
	        vagao2.setQuantidade(quantidade);
	    }

	    public void desembarcarCarga(char identificador) {
	        Vagao vagao = encontrarVagao(identificador);
	        if (vagao == null) {
	            System.out.println("Vagão com identificador " + identificador + " não encontrado.");
	            return;
	        }

	        vagao.setTipoCarga(null);
	        vagao.setQuantidade(0);
	    }

	    void transferirVagao(char identificador) {
	        Deque<Vagao> composicao1 = this.getComposicao1();
	        Deque<Vagao> composicao2 = this.getComposicao2();

	        Vagao vagao = null;
	        Deque<Vagao> origem = null;
	        Deque<Vagao> destino = null;

	        // Verifica em qual composição o vagão está presente
	        for (Vagao v : composicao1) {
	            if (v.getIdentificador() == identificador) {
	                vagao = v;
	                origem = composicao1;
	                destino = composicao2;
	                break;
	            }
	        }

	        if (vagao == null) {
	            for (Vagao v : composicao2) {
	                if (v.getIdentificador() == identificador) {
	                    vagao = v;
	                    origem = composicao2;
	                    destino = composicao1;
	                    break;
	                }
	            }
	        }

	        // Realiza a troca de composição
	        if (vagao != null && origem != null && destino != null) {
	            origem.remove(vagao);
	            destino.addLast(vagao);
	            System.out.println("Vagão " + identificador + " transferido para a outra composição.");
	        } else {
	            System.out.println("Vagão com identificador " + identificador + " não encontrado.");
	        }
	    }

	    public void adicionarVagao(Vagao vagao) {
	        if (composicao1.size() < 13) {
	            composicao1.addLast(vagao);
	        } else if (composicao2.size() < 13) {
	            composicao2.addLast(vagao);
	        } else {
	            System.out.println("As composições estão cheias. Não é possível adicionar mais vagões.");
	        }
	    }
	    
	    public void setEmMovimento(boolean emMovimento) {
	        this.emMovimento = emMovimento;
	    }

	    public boolean isEmMovimento() {
	        return emMovimento;
	    }

	    public void setAcaoAtual(String acaoAtual) {
	        this.acaoAtual = acaoAtual;
	    }

	    public String getAcaoAtual() {
	        return acaoAtual;
	    }

	    public Deque<Vagao> getComposicao1() {
	        return composicao1;
	    }

	    public Deque<Vagao> getComposicao2() {
	        return composicao2;
	    }

	    private Vagao encontrarVagao(char identificador) {
	        for (Vagao vagao1 : composicao1) {
	            if (vagao1.getIdentificador() == identificador) {
	                return vagao1;
	            }
	        }

	        for (Vagao vagao2 : composicao2) {
	            if (vagao2.getIdentificador() == identificador) {
	                return vagao2;
	            }
	        }

	        return null;
	    }
	}

