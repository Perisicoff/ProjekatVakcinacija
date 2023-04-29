package vakcinacija.UI;

import java.time.LocalDateTime;
import java.util.Collection;

import vakcinacija.DAO.PrijavaDAO;
import vakcinacija.DAO.VakcinaDAO;
import vakcinacija.model.Prijava;
import vakcinacija.model.Vakcina;
import vakcinacija.util.Konzola;

public class PrijavaUI {

	private static PrijavaDAO prijavaDAO;
	private static VakcinaDAO vakcinaDAO;

	public static void setPrijavaDAO(PrijavaDAO prijavaDAO) {
		PrijavaUI.prijavaDAO = prijavaDAO;
	}

	public static void setVakcinaDAO(VakcinaDAO vakcinaDAO) {
		PrijavaUI.vakcinaDAO = vakcinaDAO;
	}

	public static void prikazSvihPrijava() {

		try {
			Collection<Vakcina> vakcine = vakcinaDAO.getAll();
			String Headher = String.format("%-10s %-15s %-20s %-20s %-20s", "ID", "JMBG", "Ime pacijenta",
					"Naziv vakcine", "Datum primanja");
			System.out.println(Headher);
			System.out.println(
					"========== =============== ==================== ==================== ====================");
			for (Vakcina vakcina : vakcine) {
				for (Prijava prijava : vakcina.getPrijave()) {
					String foother = String.format("%-10s %-15s %-20s %-20s %-20s", prijava.getId(), prijava.getJMBG(),
							prijava.getImePacijenta(), vakcina.getNaziv(),
							(prijava.getDatum() == LocalDateTime.MIN ? "/" : Konzola.formatiraj(prijava.getDatum())));
					System.out.println(foother);
					System.out.println(
							"---------- --------------- -------------------- -------------------- --------------------");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void novaPrijava() {
		String JMBG = "";
		while (JMBG.equals("")) {
			JMBG = Konzola.ocitajString("Unesite JMBG: ");
		}
		boolean uspeh = pretragaJmbga(JMBG);
		if (uspeh == false) {
			return;
		}
		String ime = "";
		while (ime.equals("")) {
			ime = Konzola.ocitajString("Unesite ime pacijenta: ");
		}
		VakcinaUI.prikazSvihVakcina();
		long idVakcine = Konzola.ocitajLong("Unesite id zeljene Vakcine: ");
		Vakcina vakcina = VakcinaUI.pretragaVakcine(idVakcine);
		if (vakcina == null) {
			return;
		}
		LocalDateTime datum = LocalDateTime.now();
		Prijava prijava = new Prijava(JMBG, ime, datum, vakcina);
		vakcina.addPrijavu(prijava);
		try {
			prijavaDAO.add(prijava);
			System.out.println("Uspesno ste dodali novu prijavu!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static boolean pretragaJmbga(String jmbg) {
		boolean uspesno = false;
		try {
			Collection<Vakcina> vakcine = vakcinaDAO.getAll();
			for (Vakcina vakcina : vakcine) {
				for (Prijava prijava : vakcina.getPrijave()) {
					if (prijava.getJMBG().equals(jmbg)) {
						System.out.println("Prijava vec postoji za JMBG:" + jmbg);
						uspesno = false;
					} else {
						uspesno = true;
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uspesno;
	}

}
