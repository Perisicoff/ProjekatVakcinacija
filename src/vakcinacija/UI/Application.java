package vakcinacija.UI;

import java.sql.Connection;
import java.sql.DriverManager;

import vakcinacija.DAO.PrijavaDAO;
import vakcinacija.DAO.VakcinaDAO;
import vakcinacija.database.DAO.DatabasePrijavaDAO;
import vakcinacija.database.DAO.DatabaseVakcinaDAO;
import vakcinacija.util.Meni;
import vakcinacija.util.Meni.FunkcionalnaStavkaMenija;
import vakcinacija.util.Meni.IzlaznaStavkaMenija;
import vakcinacija.util.Meni.StavkaMenija;

public class Application {

	private static void initDatabase() throws Exception {
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/vakcinisanje?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Europe/Belgrade",
				"root", "root");

		PrijavaDAO prijavaDAO = new DatabasePrijavaDAO(conn);
		VakcinaDAO vakcinaDAO = new DatabaseVakcinaDAO(conn);

		PrijavaUI.setPrijavaDAO(prijavaDAO);
		PrijavaUI.setVakcinaDAO(vakcinaDAO);
		VakcinaUI.setVakcinaDAO(vakcinaDAO);
		IzvestajUI.setVakcinaDAO(vakcinaDAO);

	}

	static {
		try {
			initDatabase();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Greška pri povezivanju sa izvorom podataka!");

			System.exit(1);
		}
	}

		public static void main(String[] args) throws Exception {
			Meni.pokreni("Vakcinacija", new StavkaMenija[] {
				new IzlaznaStavkaMenija("Izlaz"),
				new FunkcionalnaStavkaMenija("Prikaz svih vakcina") {

					@Override
					public void izvrsi() { VakcinaUI.prikazSvihVakcina(); }
					
				}, 
				new FunkcionalnaStavkaMenija("Dodavanje prijave") {

					@Override
					public void izvrsi() { PrijavaUI.novaPrijava(); }
					
				}, 
				new FunkcionalnaStavkaMenija("Prikaz svih prijava") {

					@Override
					public void izvrsi() { PrijavaUI.prikazSvihPrijava(); }
					
				},
				new FunkcionalnaStavkaMenija("Izvestaj") {

					@Override
					public void izvrsi() { IzvestajUI.izvestaj(); }
					
				}
			});
		}


}
