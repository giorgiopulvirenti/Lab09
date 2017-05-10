package it.polito.tdp.metrodeparis.model;


import java.util.ArrayList;
import java.util.List;

import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph;


import it.polito.tdp.metrodeparis.dao.MetroDAO;

public class Model {
	private WeightedGraph<Fermata, FermatePairs> graph  ;
	private List<Fermata> fermate;
	MetroDAO dao=new MetroDAO();
	
	
	
	public Model(){
		
		
	
		
	}
	
	public List<Fermata> getFermate() {
		if(this.fermate==null) {
			
			this.fermate=new ArrayList(dao.getAllFermate());
		}
		System.out.println(this.fermate);
		return this.fermate ;
	}
	 
	public void creaGrafo(){
		this.graph=new SimpleWeightedGraph<Fermata,FermatePairs>(FermatePairs.class);
		
		Graphs.addAllVertices(graph, this.getFermate()) ;
		for(FermatePairs fp : dao.listCoppieFermateAdiacenti()) {
			System.out.println(fp);
			 FermatePairs d=graph.addEdge(fp.getFermata1(), fp.getFermata2()) ;
			graph.setEdgeWeight(d, fp.getTempo());
		}
	}
	public String Dijkstra(Fermata f1,Fermata f2){
		
		DijkstraShortestPath<Fermata,FermatePairs> cammino=new DijkstraShortestPath<Fermata,FermatePairs>(this.getGrafo(),f1,f2);
		return cammino.getPath().toString();
	}

	private WeightedGraph<Fermata, FermatePairs> getGrafo() {
		if(this.graph==null) {
			this.creaGrafo();
		}
		return this.graph ;
	}
}
