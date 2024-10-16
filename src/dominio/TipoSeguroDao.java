package dominio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class TipoSeguroDao {
	private static final String readallTSeguros = "SELECT idTipo, descripcion FROM tiposeguros;";

	public ArrayList<TipoSeguro> readAllTiposSeguros()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<TipoSeguro> Tipos = new ArrayList<TipoSeguro>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readallTSeguros);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				Tipos.add(getTpoSeguros(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return Tipos;
	}
	
	private TipoSeguro getTpoSeguros(ResultSet resultSet) throws SQLException
	{
		int ideSeguro = resultSet.getInt("idTipo");
		String descripcion = resultSet.getString("descripcion");
		return new TipoSeguro(ideSeguro,descripcion);

	}
}
