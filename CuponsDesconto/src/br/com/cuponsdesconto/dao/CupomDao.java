package br.com.cuponsdesconto.dao;

import br.com.cuponsdesconto.entidades.Cupom;
import br.com.cuponsdesconto.entidades.Entidade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CupomDao extends Dao implements FuncoesDao {

    @Override
    public boolean adicionar(Entidade entidade) {
        Cupom cupom = (Cupom) entidade;
        String sql = "insert into cupom (descricao, codigoCupom, numeroDePontos) values (?,?,?)";
        try{
            this.conectar();
            this.stmt = this.conn.prepareStatement(sql);
            this.stmt.setString(1, cupom.getDescricao());
            this.stmt.setInt(2, cupom.getCodigoCupom());
            this.stmt.setInt(3, cupom.getNumeroDePontos());
            this.stmt.execute();
            System.out.println("Cupom criado com sucesso!");
        } catch(SQLException ex){
            System.out.println("Erro ao inserir Cupom "+ex.getMessage());
            return false;
        }
        finally{
            try {
                this.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CupomDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    @Override
    public boolean deletar(int id) {
        String sql = "delete from cupom where id = ?";
        try {
        	this.conectar();
        	this.stmt = this.conn.prepareStatement(sql);
        	this.stmt.setInt(1, id);
        	this.stmt.execute();
        }catch(SQLException ex) {
        	System.out.println("Erro ao deletar Cupom: "+ex.getMessage());
        	return false;
        }
        return true;
    }

    @Override
    public boolean atualizar(Entidade entidade) {
        Cupom cupom = (Cupom) entidade;
        String sql = "update usuario set codigoCupom = ?, descricao = ?, numeroDePontos = ? where id = ?";
        try {
        	this.conectar();
        	this.stmt = this.conn.prepareStatement(sql);
        	this.stmt.setInt(1, cupom.getCodigoCupom());
        	this.stmt.setString(2, cupom.getDescricao());
        	this.stmt.setInt(3, cupom.getNumeroDePontos());
        	this.stmt.setInt(4, cupom.getId());
        	this.stmt.execute();
        }catch(SQLException ex) {
        	System.out.println("Erro ao atualizar Usuario: "+ex.getMessage());
        	return false;
        }
        return true;
    }

    @Override
    public Entidade buscar(int id) {
        String sql = "select * from cupom where id = ?";
        try {
        	this.conectar();
        	this.stmt = this.conn.prepareStatement(sql);
        	this.stmt.setInt(1, id);
        	ResultSet rs = this.stmt.executeQuery();
        	if(rs.next()) {
        		Cupom cupom = new Cupom();
        		cupom.setId(rs.getInt("id"));
        		cupom.setCodigoCupom(rs.getInt("codigoCupom"));
        		cupom.setDescricao(rs.getString("descricao"));
        		cupom.setNumeroDePontos(rs.getInt("numeroDePontos"));
        		return cupom;
        	}
        }catch(SQLException ex) {
        	System.out.println("Erro ao buscar Cupom: "+ex.getMessage());
        	return null;
        }
        return null;
    }

    @Override
    public List<Entidade> buscarTodos() {
        return null;
    }
    
}
