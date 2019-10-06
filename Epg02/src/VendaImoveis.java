import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Object;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Supplier;

import org.jgrapht.Graph;
import org.jgrapht.GraphType;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.*;
import org.jgrapht.io.CSVImporter;
import org.jgrapht.io.EdgeProvider;
import org.jgrapht.io.VertexProvider;
import sun.security.provider.certpath.Vertex;

import static javafx.scene.input.KeyCode.S;
import static javafx.scene.input.KeyCode.V;


public class VendaImoveis {

    private SimpleWeightedGraph<String,DefaultWeightedEdge> distrito;


    public VendaImoveis (String fileName) {
        //Inicializando um grafo ponderado
        this.distrito = new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class) ;

        //Nesse bloco, e feita a conversao do grafo no formato csv para um objeto do tipo SimpleWeightedGraph
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String sCurrentLine = br.readLine();
            while ((sCurrentLine = br.readLine()) != null) {
                String [] attributes = sCurrentLine.split(",");
                this.distrito.addVertex(attributes[0]);
                this.distrito.addVertex(attributes[1]);
                DefaultWeightedEdge e = new DefaultWeightedEdge();
                this.distrito.setEdgeWeight(e, new Double(attributes[2]).doubleValue());
                this.distrito.addEdge(attributes[0], attributes[1], e);
            }
        } catch (IOException e)  {
            e.printStackTrace();
        }


    }

    public String localizaImovel (String pontodeInteresse, Set <String> imoveis) {
        //Primeiro é checado se o metodo recebeu uma entrada null, e caso tenha recebido e retonado null
        if (pontodeInteresse == null || imoveis == null){
            return null;
        //Confere se os pontos de interesse sao vertices do grafo, caso nao e retornado null
        }else if (!this.distrito.containsVertex(pontodeInteresse)){
            return null;
        //Verifica se o Set imoveis e vazio ou se o grafo e vazio, se qualquer um dos dois forem o metodo retorna null
        }else if (this.distrito.vertexSet().size() == 0 || imoveis.size() == 0){
            return null;
        }else {

            DijkstraShortestPath<String, DefaultWeightedEdge> dsp = new DijkstraShortestPath<>(this.distrito);

            ShortestPathAlgorithm.SingleSourcePaths<String, DefaultWeightedEdge> paths = dsp.getPaths(pontodeInteresse);

            double distancia = 0;
            String melhorImovel = "";

            /*
            Apartir da funcao getWeight o programa pega o peso do caminho de um imovel para um ponto de interesse, e
            compara com o peso do caminho dos outros imoveis para o mesmo ponto de interesse e guarda o vertice cujo o
            caminho tenha o menor peso
             */
            for (String imovel : imoveis) {
                if (distancia == 0) {
                    distancia = paths.getWeight(imovel);
                    melhorImovel = imovel;
                } else if (paths.getWeight(imovel) < distancia) {
                    distancia = paths.getWeight(imovel);
                    melhorImovel = imovel;
                }
            }
            //Retorna o -melhorImovel- ou seja o imovel mais perto do ponto de interesse
            return melhorImovel;
        }
    }
}