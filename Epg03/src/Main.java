import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.io.EdgeProvider;
import org.jgrapht.io.GmlImporter;
import org.jgrapht.io.ImportException;
import org.jgrapht.io.VertexProvider;

public class Main {

    public static void main(String[] args) {
        Graph<DefaultVertex, RelationshipEdge> graph = new SimpleGraph<>(RelationshipEdge.class);
        VertexProvider <DefaultVertex> vp1 = (label,attributes) -> new DefaultVertex (label,attributes);
        EdgeProvider <DefaultVertex,RelationshipEdge> ep1 = (from,to,label,attributes) -> new RelationshipEdge(from,to,attributes);
        GmlImporter <DefaultVertex,RelationshipEdge> gmlImporter = new GmlImporter <> (vp1,ep1);

        try {
            gmlImporter.importGraph(graph, ImportGraph.readFile("./antcolony1000.gml"));
        } catch (ImportException e) {
            throw new RuntimeException(e);
        }

        relacoesDaColonia relacoesDaColonia = new relacoesDaColonia();
        System.out.println("1. Formigas que melhor atuaram na transmissao de informacao:");
        System.out.println(relacoesDaColonia.formigasMaisCondutoras(graph));
        System.out.println("2. Formigas mais influentes:");
        System.out.println(relacoesDaColonia.formigasMaisInfluentes(graph));
        System.out.println("3. Formacao de grupos isolados:");
        System.out.println(relacoesDaColonia.gruposIsolados(graph));
        System.out.println("4. Semelhanca no padrao de comunicacao");
        System.out.println(relacoesDaColonia.assortividadeFormigas(graph));
    }
}
