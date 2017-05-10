package it.polito.tdp.metrodeparis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.javadocmd.simplelatlng.LatLng;


import it.polito.tdp.metrodeparis.model.Fermata;
import it.polito.tdp.metrodeparis.model.FermatePairs;

public class MetroDAO {
	TreeMap<Integer,Fermata> mappa=new TreeMap<Integer,Fermata>();
	public List<Fermata> getAllFermate() {


		final String sql = "SELECT id_fermata, nome, coordx, coordy FROM fermata ORDER BY nome ASC";
		List<Fermata> fermate = new ArrayList<Fermata>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Fermata f = new Fermata(rs.getInt("id_fermata"), rs.getString("nome"), new LatLng(rs.getDouble("coordx"), rs.getDouble("coordy")));
				fermate.add(f);
				mappa.put(f.getIdFermata(), f);
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}

		return fermate;
   }
	public List<FermatePairs> listCoppieFermateAdiacenti() {
		final String sql = "SELECT id_stazP, id_stazA, l.id_linea, intervallo, velocita " +
				"FROM connessione, fermata f1, fermata f2, linea l " +
				"WHERE connessione.id_linea = l.id_linea " +
				"AND connessione.id_stazP = f1.id_fermata " +
				"AND connessione.id_stazA = f2.id_fermata";
		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			List<FermatePairs> list = new ArrayList<>() ;
			
			while(rs.next()) {
		//		System.out.println(mappa.get(rs.getInt("id_stazP"))+" "+mappa.get(rs.getInt("id_stazA"))+" "+rs.getInt("id_linea")+" "+rs.getInt("intervallo")+" "+rs.getInt("velocita"));
				list.add(new FermatePairs(mappa.get(rs.getInt("id_stazP")),mappa.get(rs.getInt("id_stazA")),rs.getInt("id_linea"),rs.getInt("intervallo"),rs.getInt("velocita")));
						
							}
			
			rs.close();
			conn.close();
			
			return list ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}

	
}
