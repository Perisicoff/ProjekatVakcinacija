package vakcinacija.DAO;

import java.util.Collection;

import vakcinacija.model.Vakcina;

public interface VakcinaDAO {

	public Vakcina get(long id) throws Exception;
	public Collection<Vakcina> getAll() throws Exception;
}
