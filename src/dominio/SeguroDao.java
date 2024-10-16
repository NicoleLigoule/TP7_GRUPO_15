package dominio;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class SeguroDao {
	private static final String insert ="INSERT INTO seguros (descripcion,idTipo,costoContratacion,costoAsegurado) VALUES (?,?,?,?)";
	private static final String readall = "SELECT idSeguro, descripcion, idTipo,costoContratacion,costoAsegurado FROM seguros;";
	
	
	
	
	public boolean insert(Seguro sucu)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setString(1, sucu.getDescripcion());
			statement.setInt(2, sucu.getTipoSeguro());
			statement.setBigDecimal(3, new BigDecimal(sucu.getCostoContratacion()));
			statement.setBigDecimal(4, new BigDecimal(sucu.getCostoAsegurado()));
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return isInsertExitoso;
	}
	
	public List<Seguro> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Seguro> personas = new ArrayList<Seguro>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				personas.add(getPersona(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return personas;
	}
	private Seguro getPersona(ResultSet resultSet) throws SQLException
	{
		int ideSeguro = resultSet.getInt("idSeguro");
		String descripcion = resultSet.getString("descripcion");
		int idTipo = resultSet.getInt("idTipo");
		double costoContratacion =resultSet.getBigDecimal("costoContratacion").doubleValue();
		double costoAsegurado= resultSet.getBigDecimal("costoAsegurado").doubleValue();
		return new Seguro(ideSeguro,descripcion,idTipo,costoContratacion,costoAsegurado);

	}
}
