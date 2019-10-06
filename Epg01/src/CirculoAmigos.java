import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.util.Pair;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.alg.connectivity.*;;

public class CirculoAmigos {

    /*
     * Metodo que recebe um grafo que os vertices representam pessoas e as arestas amizade e retorna
     * os possiveis circulos de amizade
     */
    public static List <Set <String>> retornaCirculos (List<Pair<String,String>> paresAmigos) {
        Graph<String,DefaultEdge> graph =
                new SimpleGraph <String,DefaultEdge> (DefaultEdge.class);

        //dentro desse for e montado o grafo.
        for ( int i = 0; i < paresAmigos.size(); i++) {
            if(!(graph.containsVertex(paresAmigos.get(i).getFirst()))){
                graph.addVertex(paresAmigos.get(i).getFirst());
            }
            if(!(graph.containsVertex(paresAmigos.get(i).getSecond()))){
                graph.addVertex(paresAmigos.get(i).getSecond());
            }
            if (!(graph.containsEdge(paresAmigos.get(i).getFirst(), paresAmigos.get(i).getSecond()))) {
                graph.addEdge(paresAmigos.get(i).getFirst(),paresAmigos.get(i).getSecond());
            }
        }

        //Criando um objeto do tipo ConnectivityInspector
        ConnectivityInspector<String, DefaultEdge> connectivityInspector = new ConnectivityInspector<String, DefaultEdge>(graph);

        //Essa funcao da clase connectivityInspector retorna conjuntos de vertices que estao conectado
        return connectivityInspector.connectedSets();

    }

}
