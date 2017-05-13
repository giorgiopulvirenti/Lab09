package it.polito.tdp.metrodeparis.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.graph.SimpleWeightedGraph;


import it.polito.tdp.metrodeparis.dao.MetroDAO;

public class Model {
	private WeightedGraph<Fermata, Linea> graph  ;
	private List<Fermata> fermate;
	MetroDAO dao=new MetroDAO();
	int linea;
	Map<Fermata,Integer> coincide=new TreeMap<Fermata,Integer>();
	
	
	
	public Model(){
		
		
	
		
	}
	
	public List<Fermata> getFermate() {
		if(this.fermate==null) {
			
			this.fermate=new ArrayList(dao.getAllFermate());
		}
		
		return this.fermate ;
	}
	 
	public void creaGrafo(){
		this.graph=new SimpleDirectedWeightedGraph<Fermata,Linea>(Linea.class);
		
		Graphs.addAllVertices(graph, this.getFermate()) ;
		List <FermatePairs> pairs=new ArrayList<FermatePairs>(dao.listCoppieFermateAdiacenti());
		linea=pairs.get(0).getId();
		for(FermatePairs fp : pairs) {
			
		//	System.out.println(fp.getFermata1()+" ----  "+fp.getFermata2()+" ----  "+fp.getFermata1().getCodice()+" ----  "+fp.getId());
			
			
			
		//	for (Linea  l: graph.edgeSet())
	//		if (fp.getFermata1().equals(graph.getEdgeSource(l))&&l.getId()!=fp.getId()){
			
	//			Fermata f1= new Fermata(fp.getFermata1().getIdFermata()+1000,fp.getFermata1().getNome(),fp.getFermata1().getCoords());
	//			graph.addVertex(f1);
	//			System.out.println(fp.getFermata1()+"  ////  "+f1);
	//			Linea l1=graph.addEdge(fp.getFermata1(), f1) ;
				
	//			if(l1!=null){
	//				l1.setId(fp.getId());
	//				l1.setIntervallo(fp.getIntervallo());
	//				l1.setVelocita(fp.getVelocita());
				
	//				graph.setEdgeWeight(l1, l1.getIntervallo()/1000);
	//			}
	//			System.out.println(f1+" ### "+fp.getFermata2());
	//				Linea l2=	graph.addEdge(f1, fp.getFermata2()) ;
					
	//				if(l2!=null){
	//					l2.setId(fp.getId());
	//					l2.setIntervallo(fp.getIntervallo());
	//					l2.setVelocita(fp.getVelocita());
		//				l2.setTempo(fp.getFermata1(), fp.getFermata2());
//						graph.setEdgeWeight(l2, l2.getTempo());
	//			}
				
		//	}
	//			else{
//					System.out.println(fp.getFermata1()+"     "+fp.getFermata2());
				
	Linea l=graph.addEdge(fp.getFermata1(), fp.getFermata2()) ;
	
	
			if(l!=null){
				l.setId(fp.getId());
				l.setIntervallo(fp.getIntervallo());
				l.setVelocita(fp.getVelocita());
				l.setTempo(fp.getFermata1(), fp.getFermata2());
	
				
			graph.setEdgeWeight(l, l.getTempo());
			}
			
		//	fp.getFermata1().setCodice(fp.getId());
		}
	}
	public String Dijkstra(Fermata f1,Fermata f2){
    //	System.out.println(f1+" "+f2);
		DijkstraShortestPath<Fermata,Linea> cammino=new DijkstraShortestPath<Fermata,Linea>(this.getGrafo(),f1,f2);
//		System.out.println(cammino);
		List<Linea> lista=new ArrayList<Linea>(cammino.getPathEdgeList());
	String s="";
	int con=lista.get(0).getId();
	
		for(Linea f:cammino.getPathEdgeList()){
			if (con!=f.getId()&&f.getFlag()!=1){
				graph.setEdgeWeight(f, graph.getEdgeWeight(f)+f.getIntervallo());
				f.setFlag(1);
		return	this.Dijkstra(f1, f2);
			}
			if(con==f.getId()&&f.getFlag()==1){
				graph.setEdgeWeight(f, graph.getEdgeWeight(f)-f.getIntervallo());
				f.setFlag(0);
				return	this.Dijkstra(f1, f2);
				
			}
//			System.out.println((this.getGrafo().getEdgeSource(f).getNome()+" @@ "+this.getGrafo().getEdgeTarget(f).getNome()));
			
			if (con!=f.getId()){
				s+="Cambio Linea "+f.getId()+"\n";
//				System.out.println((this.getGrafo().getEdgeSource(f).getNome()+" ## "+this.getGrafo().getEdgeTarget(f).getNome()));
			}
			
		s+=f.toString()+" Partenza-- "+this.getGrafo().getEdgeSource(f)+"; Arrivo-- "+this.getGrafo().getEdgeTarget(f)+"\n";
		con=f.getId();
		}
		
		return s.trim();
	
	}

	private WeightedGraph<Fermata, Linea> getGrafo() {
		if(this.graph==null) {
			this.creaGrafo();
		}
		return this.graph ;
	}
}
