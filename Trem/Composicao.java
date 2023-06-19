package Trem;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Composicao {
	private Stack<Vagao> vagoes;

    public Composicao() {
        vagoes = new Stack<>();
    }

    public void adicionarVagao(Vagao vagao) {
        vagoes.push(vagao);
    }

    public Vagao removerVagao() {
        if (!vagoes.isEmpty()) {
            return vagoes.pop();
        }
        return null;
    }

    public Stack<Vagao> getVagoes() {
        return vagoes;
    }
}

