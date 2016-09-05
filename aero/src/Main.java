import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Maicon Machado Gerardi da Silva
 * @author Débora Ghilardi
 */
class Nodo {

	private String nome;
	private List<Nodo> filhos;

	public Nodo(String nome) {
		this.nome = nome;
		this.filhos = new ArrayList<Nodo>();
	}

	public void adicionarFilho(Nodo nodo) {
		this.filhos.add(nodo);
	}

	public int quantidadeDeFilhos() {
		return filhos.size();
	}

	public String getNome() {
		return nome;
	}

}

class Grafo {

	private Map<String, Nodo> nodos;

	public Grafo() {
		nodos = new HashMap<String, Nodo>();
	}

	public Nodo adicionarNodo(Nodo nodo) {
		nodos.put(nodo.getNome(), nodo);
		return nodos.get(nodo.getNome());
	}

	public void criarAresta(String nomeNodo1, String nomeNodo2) {
		Nodo nodo1 = nodos.get(nomeNodo1);
		Nodo nodo2 = nodos.get(nomeNodo2);

		nodo1.adicionarFilho(nodo2);
		nodo2.adicionarFilho(nodo1);
	}

	public String nodosComMaiorQuantidadeDeFilhos() {
		Set<String> keySet = nodos.keySet();
		List<Integer> maiores = new ArrayList<Integer>();
		int maiorQtdFilhos = -1;
		for (String chave : keySet) {
			Nodo nodo = nodos.get(chave);

			int qtdDeFilhosDoNodo = nodo.quantidadeDeFilhos();
			if (qtdDeFilhosDoNodo > maiorQtdFilhos) {
				maiorQtdFilhos = qtdDeFilhosDoNodo;
				maiores.clear();
				maiores.add(Integer.parseInt(nodo.getNome()));
			} else if (qtdDeFilhosDoNodo == maiorQtdFilhos) {
				maiores.add(Integer.parseInt(nodo.getNome()));
			}
		}

		StringBuilder maioreStr = new StringBuilder();
		Collections.sort(maiores);
		for (Integer str : maiores) {
			maioreStr.append(str.toString());
			maioreStr.append(" ");
		}
		return maioreStr.toString().trim();
	}
}

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder resultado = new StringBuilder();

		int contador = 1;
		while (true) {
			Grafo grafo = new Grafo();

			String linha = teclado.readLine();
			String[] aeroportosEvoos = linha.split(" ");

			int aeroportos = Integer.parseInt(aeroportosEvoos[0]);
			if (aeroportos < 0 || aeroportos > 100) {
				throw new Exception("0 <= A <= 100 (A = 0 apenas para indicar o fim da entrada)");
			}

			int voos = Integer.parseInt(aeroportosEvoos[1]);

			if (voos < 0 || voos > 10000) {
				throw new Exception("0 <= V <= 10000 (V = 0 apenas para indicar o fim da entrada)");
			}

			if (aeroportos == 0 && voos == 0) {
				break;
			}

			if(aeroportos == 1 && voos == 0){
				resultado.append("Teste ");
				resultado.append(contador++);
				resultado.append(System.getProperty("line.separator"));
				resultado.append(" ");
				resultado.append(System.getProperty("line.separator"));
				resultado.append(System.getProperty("line.separator"));
				continue;
			}
			
			if (aeroportos == 0 || voos == 0) {
				throw new Exception("A = 0 e V = 0 apenas para indicar o fim da entrada");
			}

			for (int i = 1; i <= aeroportos; i++) {
				grafo.adicionarNodo(new Nodo(String.valueOf(i)));
			}

			for (int i = 0; i < voos; i++) {
				linha = teclado.readLine();
				String[] rota = linha.split(" ");

				int rota0 = Integer.parseInt(rota[0]);
				int rota1 = Integer.parseInt(rota[1]);
				if (rota0 < 1 || rota0 > aeroportos) {
					throw new Exception("1 <= X <= A");
				}
				if (rota1 < 1 || rota1 > aeroportos) {
					throw new Exception("1 <= X <= A");
				}
				if (rota0 == rota1) {
					throw new Exception("X != Y");
				}

				grafo.criarAresta(rota[0], rota[1]);
			}

			resultado.append("Teste ");
			resultado.append(contador++);
			resultado.append(System.getProperty("line.separator"));
			resultado.append(grafo.nodosComMaiorQuantidadeDeFilhos());
			resultado.append(System.getProperty("line.separator"));
			resultado.append(System.getProperty("line.separator"));
		}

		System.out.println(resultado.toString());
	}

}
