package principal.scrabble;

import org.jgrapht.*;
import org.jgrapht.graph.*;

public class ClasePrincipalGrafos {
	
	public static void main(String[] args) {
		
		Graph<String, DefaultEdge> dag = new DefaultDirectedGraph<>(DefaultEdge.class);
		
		// Agregar vértices
        dag.addVertex("V1");
        dag.addVertex("V2");
        dag.addVertex("V3");
        dag.addVertex("V4");
        
     // Agregar aristas
        dag.addEdge("V1", "V2");
        dag.addEdge("V1", "V3");
        dag.addEdge("V2", "V4");
        dag.addEdge("V3", "V4");
        
     // Imprimir el grafo
        System.out.println("Vertices: " + dag.vertexSet());
        System.out.println("Aristas: " + dag.edgeSet());

		
	
		
	}

}
