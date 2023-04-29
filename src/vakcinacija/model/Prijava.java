package vakcinacija.model;

import java.time.LocalDateTime;
import java.util.Objects;

import vakcinacija.util.Konzola;

public class Prijava {

	private long id;
	private String JMBG = "";
	private String imePacijenta = "";
	private LocalDateTime datum = LocalDateTime.now();

	Vakcina vakcina;

	public Prijava() {
		super();
	}

	public Prijava(String jMBG, String imePacijenta, LocalDateTime datum, Vakcina vakcina) {
		super();
		JMBG = jMBG;
		this.imePacijenta = imePacijenta;
		this.datum = datum;
		this.vakcina = vakcina;
	}

	public Prijava(long id, String jMBG, String imePacijenta, LocalDateTime datum, Vakcina vakcina) {
		super();
		this.id = id;
		JMBG = jMBG;
		this.imePacijenta = imePacijenta;
		this.datum = datum;
		this.vakcina = vakcina;
	}

	public boolean isDatumUOpsegu(LocalDateTime pocetni, LocalDateTime krajnji) {
		return datum.compareTo(pocetni) >= 0 && datum.compareTo(krajnji) <= 0;

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
		Prijava other = (Prijava) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "ID projave: " + id + "   JMBG: " + JMBG + "   Ime pacijenta: " + imePacijenta + "   Datum primanja: "
				+ (datum == LocalDateTime.MIN ? "/" : Konzola.formatiraj(datum)) + "   Naziv vakcine: "
				+ vakcina.getNaziv();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getJMBG() {
		return JMBG;
	}

	public void setJMBG(String jMBG) {
		JMBG = jMBG;
	}

	public String getImePacijenta() {
		return imePacijenta;
	}

	public void setImePacijenta(String imePacijenta) {
		this.imePacijenta = imePacijenta;
	}

	public LocalDateTime getDatum() {
		return datum;
	}

	public void setDatum(LocalDateTime datum) {
		this.datum = datum;
	}

	public Vakcina getVakcina() {
		return vakcina;
	}

	public void setVakcina(Vakcina vakcina) {
		this.vakcina = vakcina;
		vakcina.prijave.add(this);
	}

}
