package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	private Map<Integer, Country> codeMap;
	private BordersDAO dao;
	private Graph<Country, DefaultEdge> graph;
	public Model() {
		this.codeMap=new HashMap<Integer,Country>();
		this.dao=new BordersDAO();
		this.dao.loadAllCountries(codeMap);
	}
	public void buildgraph(int anno) {
		
		this.graph= new SimpleGraph<>(DefaultEdge.class);
		List<Border> confini= this.dao.getCountryPairs(anno, codeMap);
		if(confini.isEmpty()) {
			throw new RuntimeException("non esistono confini per quell'anno");
		}
		for(Border b: confini) {
			graph.addVertex(b.getC1());
			graph.addVertex(b.getC2());
			graph.addEdge(b.getC1(), b.getC2());
		}
	}
	public List<CountryAndNumber> elencoStati(){
		if(graph==null){
			throw new RuntimeException("Grafo non esistente");
		}
		List<CountryAndNumber> result= new ArrayList<>();
		for(Country c: graph.vertexSet()) {
			result.add(new CountryAndNumber(c, this.graph.degreeOf(c)));
		}
		return result;
	}
	public int componenteConnessa() {
		if(graph==null){
			throw new RuntimeException("Grafo non esistente");
		}
		ConnectivityInspector<Country, DefaultEdge> ci = new ConnectivityInspector<Country, DefaultEdge>(graph);
		return ci.connectedSets().size();
	}
	//versione libreria
	public List<Country> paesiVicini(Country partenza){
		List<Country> result= new ArrayList<>();
		GraphIterator<Country, DefaultEdge> iteratore = new DepthFirstIterator<Country, DefaultEdge>(graph, partenza);
		while (iteratore.hasNext()) {
			result.add(iteratore.next());
		}

		return result;
	}
	//verssione iterativa
	public List<Country> paesiViciniIterativo(Country partenza) {
		List<Country> visti = new ArrayList<Country>();

		List<Country> nonVisti = new ArrayList<Country>();
		nonVisti.add(partenza);
		while(!nonVisti.isEmpty()) {
			Country c=nonVisti.remove(0);
			visti.add(c);
			List<Country> lTemp=Graphs.neighborListOf(this.graph, c);
			for(Country cTemp: lTemp) {
				if(!visti.contains(cTemp)) {
					nonVisti.add(cTemp);
				}
			}
		}
		return visti;
	}
	public List<Country> paesiViciniRicorsivo(Country partenza){
		List<Country> visti=new ArrayList<>();
		this.ricorsione(partenza, visti);
		return visti;
	}
	
	public void ricorsione(Country n, List<Country> parziale) {
		parziale.add(n);
		for(Country c: Graphs.neighborListOf(graph, n)) {
			if(!parziale.contains(c)) {
				ricorsione(c, parziale);
			}
		}
	}
	}
