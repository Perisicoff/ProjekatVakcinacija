package vakcinacija.database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import vakcinacija.DAO.VakcinaDAO;
import vakcinacija.model.Prijava;
import vakcinacija.model.Vakcina;

public class DatabaseVakcinaDAO implements VakcinaDAO {

	private final Connection conn;

	public DatabaseVakcinaDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public Collection<Vakcina> getAll() throws Exception {
		Map<Long, Vakcina> vakcine = new LinkedHashMap<>();
		
		String sql = "SELECT v.id, v.naziv, v.tip, v.temperatura, p.id, p.jmbg, p.imePacijenta, p.datumIVreme FROM vakcinisanje.vakcine v LEFT JOIN vakcinisanje.prijave p ON v.id = p.vakcinaId";
		try(PreparedStatement stmt = conn.prepareStatement(sql)) {
			try(ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int kolona = 0;
					long vId = rset.getLong(++kolona);
					String vNaziv = rset.getString(++kolona);
					String vTip = rset.getString(++kolona);
					int vTemp = rset.getInt(++kolona);
					
					Vakcina vakcina = vakcine.get(vId);
					if (vakcina == null) {
						vakcina = new Vakcina(vId, vNaziv, vTip, vTemp);
						vakcine.put(vakcina.getId(), vakcina);
					}	
					
					long pId = rset.getLong(++kolona);
					if (pId != 0) {
						String pJmbg = rset.getString(++kolona);
						String pIme = rset.getString(++kolona);
						LocalDateTime datum = rset.getTimestamp(++kolona).toLocalDateTime();
						Prijava prijava = new Prijava(pId, pJmbg, pIme, datum, vakcina);
						vakcina.addPrijavu(prijava);
					}
					
				}
			}
		}
		
		return vakcine.values();
	}

	@Override
	public Vakcina get(long id) throws Exception {
		Vakcina vakcina = null;

		String sql = "SELECT naziv, tip, temperatura FROM vakcine WHERE id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int param = 0;
			stmt.setLong(++param, id);
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int kolona = 0;
					String naziv = rset.getString(++kolona);
					String tip = rset.getString(++kolona);
					int temp = rset.getInt(++kolona);
					vakcina = new Vakcina(id, naziv, tip, temp);
				}
			}
		}
		return vakcina;
	}

}
