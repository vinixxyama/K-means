import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Cluster{
	private ArrayList<Centroide> centro2;
	private Double x;
	private Double y;

	public Cluster(Double x, Double y){
		this.x = x;
		this.y = y;
		centro2 = new ArrayList<>();
	}
	public void recalc(){
		this.centro2 = new ArrayList<>();
	}
	public Double getx(){
		return x;
	}
	public Double gety(){
		return y;
	}
	public void setx(Double x){
		this.x = x;
	}
	public void sety(Double y){
		this.y = y;
	}
	public Double getCentx(int i){
		return centro2.get(i).getx();
	}
	public Double getCenty(int i){
		return centro2.get(i).gety();
	}
	public void addArray(Centroide c){
		centro2.add(c);
	}
	public int getSize(){
		return centro2.size();
	}
	public String getNomeCentroide(int i){
		return centro2.get(i).getNome();
	}
}