package vakcinacija.model;

import java.time.LocalDateTime;

import vakcinacija.util.Konzola;

public class StavkaIzvestaja {

	private String nazivVakcine = "";
	private int ukupanBrojPrijava;
	private LocalDateTime poslednjaPrijava = LocalDateTime.now();

	public StavkaIzvestaja(String nazivVakcine, int ukupanBrojPrijava, LocalDateTime poslednjaPrijava) {
		super();
		this.nazivVakcine = nazivVakcine;
		this.ukupanBrojPrijava = ukupanBrojPrijava;
		this.poslednjaPrijava = poslednjaPrijava;
	}

	@Override
	public String toString() {
		return "Naziv vakcine: " + nazivVakcine + "   Ukupan broj prijava: " + ukupanBrojPrijava
				+ "   Poslednja prijava: " + (poslednjaPrijava == LocalDateTime.MIN ? "/" : Konzola.formatiraj(poslednjaPrijava));
	}

	public static int compareBrojPrijava(StavkaIzvestaja stavka1, StavkaIzvestaja stavka2) {
		return Integer.compare(stavka1.getUkupanBrojPrijava(), stavka2.getUkupanBrojPrijava());

	}

	public String getNazivVakcine() {
		return nazivVakcine;
	}

	public int getUkupanBrojPrijava() {
		return ukupanBrojPrijava;
	}

	public LocalDateTime getPoslednjaPrijava() {
		return poslednjaPrijava;
	}

}
