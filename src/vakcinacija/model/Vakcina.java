package vakcinacija.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Vakcina {

	private long id;
	private String naziv = "";
	private String tip = "";
	private int temp;

	final Set<Prijava> prijave = new HashSet<>();

	public Vakcina() {
		super();
	}

	public Vakcina(String naziv, String tip, int temp) {
		super();
		this.naziv = naziv;
		this.tip = tip;
		this.temp = temp;
	}

	public Vakcina(long id, String naziv, String tip, int temp) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.tip = tip;
		this.temp = temp;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vakcina other = (Vakcina) obj;
		return id == other.id;
	}
	
	public String znak() {
		String znak = "";
		if (temp > 0) {
			znak = "+";
		}
		return znak;
	}

	@Override
	public String toString() {
		return "ID vakcine: " + id + "   Naziv: " + naziv + "   Tip: " + tip + "   Temperatura cuvanja: " + znak() + temp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public int getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

	public Collection<Prijava> getPrijave() {
		return Collections.unmodifiableCollection(prijave);
	}

	public void addPrijavu(Prijava prijava) {
		prijave.add(prijava);
		prijava.vakcina = this;
	}

	public void addSvePrijave(Collection<Prijava> prijave) {
		prijave.addAll(prijave);
		for (Prijava prijava : prijave) {
			prijava.vakcina = this;
		}

	}

	public void removePrijavu(Prijava prijava) {
		prijava.vakcina = null;
		prijave.remove(prijava);

	}

	public void removeSvePrijave() {
		for (Prijava prijava : prijave) {
			prijava.vakcina = null;
		}
		prijave.clear();
	}
}
