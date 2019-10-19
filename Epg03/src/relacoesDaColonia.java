import org.jgrapht.Graph;
import org.jgrapht.alg.scoring.AlphaCentrality;
import org.jgrapht.alg.scoring.BetweennessCentrality;
import org.jgrapht.alg.scoring.ClusteringCoefficient;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.traverse.BreadthFirstIterator;

import java.util.*;


public class relacoesDaColonia {

    /**
     * Metodo que calcula a formiga mais eficientes na conducao de informacoes usando a metrica betweenness centrality
     * @param graph grafo que esta sendo analisado
     * @return os 5 vertices mais eficientes na conducao de informacao
     */
    public String formigasMaisCondutoras(Graph <DefaultVertex, RelationshipEdge> graph) {
        BetweennessCentrality<DefaultVertex, RelationshipEdge> betweennessCentrality = new BetweennessCentrality <> (graph,true);
        return ordenaFormigas(betweennessCentrality.getScores());
    }

    /**
     * Metodo que calcula as formigas mias influentes usando a metrica Eisen Vector Centrality
     * @param graph grafo que esta sendo analizado
     * @return os 5 vertices mais influentes
     */
    public String formigasMaisInfluentes(Graph <DefaultVertex, RelationshipEdge> graph) {
        AlphaCentrality<DefaultVertex, RelationshipEdge> ac = new AlphaCentrality <> (graph,0.1, 0);
        return ordenaFormigas(ac.getScores());

    }

    /**
     * Metodo que verifica a possibilidade de grupos de formigas isolados
     * @param graph grafo que esta sendo analisado
     * @return uma mensagem dizendo se o grafo tem a possibilidade de ser separado em grupos isolados
     */
    public String gruposIsolados(Graph <DefaultVertex, RelationshipEdge> graph) {
        ClusteringCoefficient clusteringCoefficient = new ClusteringCoefficient(graph);

        double coeficienteCluster = clusteringCoefficient.getGlobalClusteringCoefficient();
        String result;
        if (coeficienteCluster< 0.5){
            result = "Nao e possivele observar a formacao de grupos isolados de formigas que se comunicam entre si, " +
                    "pois o coeficiente de cluster desse grafo e " + coeficienteCluster + " que e muito proximo de zero, o que sinaliza que as " +
                    "formigas possuem uma baixa tendencia a se organizar em grupos";
        }else {
            result = "E possivele observar a formacao de grupos isolados de formigas que se comunicam entre si, " +
                    "pois o coeficiente de cluster desse grafo e " + coeficienteCluster + " que nao e muito proximo de zero, o que sinaliza que as " +
                    "formigas possuem uma alta tendencia a se organizar em grupos";
        }
        return result;

    }

    /**
     * Metodo que verifica se existe semelhanca no padrao de comunicacao entre as formigas
     * @param graph grafo que esta sendo analisado
     * @return uma mensagem dizendo o coeficiente de assortividade do grafo
     */
    public String assortividadeFormigas(Graph<DefaultVertex, RelationshipEdge> graph) {
        double edgeCount = graph.edgeSet().size();
        double n1 = 0, n2 = 0, dn = 0;

        for (RelationshipEdge e : graph.edgeSet()) {
            int d1 = graph.degreeOf(graph.getEdgeSource(e));
            int d2 = graph.degreeOf(graph.getEdgeTarget(e));

            n1 += d1 * d2;
            n2 += d1 + d2;
            dn += d1 * d1 + d2 * d2;
        }
        n1 /= edgeCount;
        n2 = (n2 / (2 * edgeCount)) * (n2 / (2 * edgeCount));
        dn /= (2 * edgeCount);

        String result = "";

        if((n1 - n2) / (dn - n2) == 1){
            result = "Como o coeficiente de assortividade e " + (n1 - n2) / (dn - n2) + " , o grafo possui perfeita assortividade" ;
        }else if((n1 - n2) / (dn - n2) == 0){
            result = "Como o coeficiente de assortividade e " + (n1 - n2) / (dn - n2) + " , o grafo nao possui assortividade" ;
        }else if((n1 - n2) / (dn - n2) == -1){
            result = "Como o coeficiente de assortividade e " + (n1 - n2) / (dn - n2) + " , o grafo e completamente nao assortivo" ;
        }else if((n1 - n2) / (dn - n2) > -1 && (n1 - n2) / (dn - n2) < 0){
            result = "Como o coeficiente de assortividade e " + (n1 - n2) / (dn - n2) + " , o grafo e nao assortivo" ;
        }else if((n1 - n2) / (dn - n2) < 1 && (n1 - n2) / (dn - n2) > 0){
            result = "Como o coeficiente de assortividade e " + (n1 - n2) / (dn - n2) + " , o grafo possui assortividade" ;
        }

        return result;
    }

    private static <V> String ordenaFormigas(Map<V,Double> M) {
        Set<Map.Entry<V, Double>> set = M.entrySet();
        List<Map.Entry<V, Double>> list = new ArrayList<Map.Entry<V, Double>>(set);
        Collections.sort( list, new Comparator<Map.Entry<V, Double>>()
        {
            public int compare( Map.Entry<V, Double> o1, Map.Entry<V, Double> o2 ) {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );

        int count = 5;

        String s = "";

        for (int i = 0; i<count; i++) {
            Map.Entry<V,Double> e = list.get(i);
            s += e.getKey() + " - ";
        }
        return s.substring(0, s.length()-3);
    }
}
