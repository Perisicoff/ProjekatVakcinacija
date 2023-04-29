package vakcinacija.database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import vakcinacija.DAO.PrijavaDAO;
import vakcinacija.model.Prijava;

public class DatabasePrijavaDAO implements PrijavaDAO {

	private final Connection conn;

	public DatabasePrijavaDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public void add(Prijava prijava) throws Exception {
		
		String sql = "INSERT INTO prijave (id, jmbg, imePacijenta, vakcinaId, datumIVreme) VALUES (?, ?, ?, ?, ?)";
		try(PreparedStatement stmt = conn.prepareStatement(sql)) {
			int kolona = 0;
			stmt.setLong(++kolona, prijava.getId());
			stmt.setString(++kolona, prijava.getJMBG());
			stmt.setString(++kolona, prijava.getImePacijenta());
			stmt.setLong(++kolona, prijava.getVakcina().getId());
			stmt.setTimestamp(++kolona, Timestamp.valueOf(prijava.getDatum()));
			stmt.executeUpdate();
		}
		
	}

}
