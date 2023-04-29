package vakcinacija.UI;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import vakcinacija.DAO.VakcinaDAO;
import vakcinacija.model.Prijava;
import vakcinacija.model.StavkaIzvestaja;
import vakcinacija.model.Vakcina;
import vakcinacija.util.Konzola;

public class IzvestajUI {

	private static VakcinaDAO vakcinaDAO;

	public static void setVakcinaDAO(VakcinaDAO vakcinaDAO) {
		IzvestajUI.vakcinaDAO = vakcinaDAO;
	}

	public static void izvestaj() {

		LocalDateTime pocetniDatum = Konzola.ocitajDateTime("Unesite pocetni datum pretrage: ");
		LocalDateTime krajnjiDatum = Konzola.ocitajDateTime("Unesite krajnji datum pretrage: ");

		try {
			Set<String> nazivi = new LinkedHashSet<>();
			Collection<Vakcina> vakcine = vakcinaDAO.getAll();
			for (Vakcina vakcina : vakcine) {
				nazivi.add(vakcina.getNaziv());
			}
			List<StavkaIzvestaja> stavke = new ArrayList<>();
			for (String naziv : nazivi) {
				int ukupanBrojPrijava = 0;
				LocalDateTime datum = LocalDateTime.MIN;
				for (Vakcina vakcina : vakcine) {
					for (Prijava prijava : vakcina.getPrijave()) {
						if (prijava.isDatumUOpsegu(pocetniDatum, krajnjiDatum)
								&& prijava.getVakcina().getNaziv().equals(naziv)) {

							ukupanBrojPrijava = vakcina.getPrijave().size();
							if (prijava.getDatum().isAfter(datum)) {
								datum = prijava.getDatum();

							}
						}
					}
				}
				StavkaIzvestaja stavka = new StavkaIzvestaja(naziv, ukupanBrojPrijava, datum);
				stavke.add(stavka);
			}
			stavke.sort(StavkaIzvestaja::compareBrojPrijava);
			try {	
				String Headher = String.format("%-20s %-15s %-20s", "Naziv", "Broj prijava", "Datum prijave");
				System.out.println(Headher);
				System.out.println("==================== =============== ====================");
				for (StavkaIzvestaja stavkaIzvestaja : stavke) {
				String foother = String.format("%-20s %-15s %-20s", stavkaIzvestaja.getNazivVakcine(), stavkaIzvestaja.getUkupanBrojPrijava(), (stavkaIzvestaja.getPoslednjaPrijava() == LocalDateTime.MIN ? "/" : Konzola.formatiraj(stavkaIzvestaja.getPoslednjaPrijava())));
				System.out.println(foother);
				System.out.println("-------------------- --------------- --------------------");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
