package vakcinacija.UI;

import java.util.Collection;

import vakcinacija.DAO.VakcinaDAO;
import vakcinacija.model.Vakcina;

public class VakcinaUI {

	private static VakcinaDAO vakcinaDAO;

	public static void setVakcinaDAO(VakcinaDAO vakcinaDAO) {
		VakcinaUI.vakcinaDAO = vakcinaDAO;
	}

	public static void prikazSvihVakcina() {

		try {
			Collection<Vakcina> vakcine = vakcinaDAO.getAll();
			String Headher = String.format("%-10s %-20s %-30s %-20s", "ID", "Naziv", "Tip", "Temperatura cuvanja");
			System.out.println(Headher);
			System.out.println("========== ==================== =============================== ====================");
			for (Vakcina vakcina : vakcine) {
				String foother = String.format("%-10s %-20s %-30s %-20s", vakcina.getId(), vakcina.getNaziv(), vakcina.getTip(), vakcina.znak() + vakcina.getTemp());
				System.out.println(foother);
				System.out.println("---------- -------------------- ------------------------------ --------------------");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static Vakcina pretragaVakcine(long id) {
		Vakcina vakcina = null;
		try {
			vakcina = vakcinaDAO.get(id);
			if (vakcina == null) {
				System.out.println("Vakcina ne postoji sa IDem: " + id);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vakcina;

	}
}
