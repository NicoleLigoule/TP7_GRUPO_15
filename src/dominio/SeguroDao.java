package dominio;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class SeguroDao {
private static final String insert ="INSERT INTO seguros (descripcion, idTipo, costoContratacion, costoAsegurado) VALUES (?,?,?,?)";
private static final String readall = "SELECT s.idSeguro, s.descripcion, t.descripcion AS descripcionTipoSeguro, s.costoContratacion, s.costoAsegurado FROM seguros s INNER JOIN tipoSeguros t ON s.idTipo = t.idTipo";
private static final String UltimoID = "SELECT MAX(idSeguro) AS ultimoId FROM seguros;";
		
	public String UltimoId() {
		String Id="0";
		
		PreparedStatement statement;
		ResultSet resultSet; 
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(UltimoID);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				int CambioId= resultSet.getInt("ultimoId");
				CambioId=CambioId+1;
				Id =String.valueOf(CambioId);
						}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return Id;
	}
	
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
	
	public ArrayList<Seguro> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Seguro> seguro = new ArrayList<Seguro>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				seguro.add(getSeguro(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return seguro;
	}
	
	public ArrayList<Seguro> readFiltro(int idTipo)
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Seguro> seguro = new ArrayList<Seguro>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall + " where s.idtipo =" + idTipo);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				seguro.add(getSeguro(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return seguro;
	}
	
	
	private Seguro getSeguro(ResultSet resultSet) throws SQLException
	{
	    int ideSeguro = resultSet.getInt("idSeguro");
	    String descripcion = resultSet.getString("descripcion");
	    String descripcionTipoSeguro = resultSet.getString("descripcionTipoSeguro");
	    double costoContratacion = resultSet.getBigDecimal("costoContratacion").doubleValue();
	    double costoAsegurado = resultSet.getBigDecimal("costoAsegurado").doubleValue();
	    
	    Seguro seguro = new Seguro(ideSeguro, descripcion, descripcionTipoSeguro, costoContratacion, costoAsegurado);  
	    return seguro;
	}

}
