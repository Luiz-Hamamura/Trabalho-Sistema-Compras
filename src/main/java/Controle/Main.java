package Controle;

import Entidade.Produto;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.annotation.PostConstruct;

@ManagedBean(name = "main")
@SessionScoped
public class Main implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private double valor;
    private int estoque;

    private List<Produto> listaProdutos = new ArrayList<>();

    @PostConstruct
    public void iniciar() {
        consultar();
    }

    public void editar(Produto p) {

        id = p.getId();
        nome = p.getNome();
        valor = p.getValor();
        estoque = p.getEstoque();
    }

    public void atualizar() {

        try {

            Class.forName("org.postgresql.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/Sistema De Comprar",
                    "postgres",
                    "2627"
            );

            String sql = "UPDATE estoque "
                    + "SET nome = ?, valor = ?, quantidade = ? "
                    + "WHERE id = ?";

            PreparedStatement stm = con.prepareStatement(sql);

            stm.setString(1, nome);
            stm.setDouble(2, valor);
            stm.setInt(3, estoque);
            stm.setInt(4, id);

            stm.executeUpdate();

            stm.close();
            con.close();

            consultar();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {

        try {

            Class.forName("org.postgresql.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/Sistema De Comprar",
                    "postgres",
                    "2627"
            );

            String sql = "DELETE FROM estoque WHERE id = ?";

            PreparedStatement stm = con.prepareStatement(sql);

            stm.setInt(1, id);

            stm.executeUpdate();

            stm.close();
            con.close();

            consultar();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void salvar() {

        try {

            Produto p = new Produto();

            p.setNome(nome);
            p.setValor(valor);
            p.setEstoque(estoque);

            Class.forName("org.postgresql.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/Sistema De Comprar",
                    "postgres",
                    "2627"
            );

            String sql = "INSERT INTO estoque (nome, valor, quantidade) VALUES (?, ?, ?)";

            PreparedStatement stm = con.prepareStatement(sql);

            stm.setString(1, p.getNome());
            stm.setDouble(2, p.getValor());
            stm.setInt(3, p.getEstoque());

            stm.executeUpdate();

            stm.close();
            con.close();

            consultar();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consultar() {

        try {

            Class.forName("org.postgresql.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/Sistema De Comprar",
                    "postgres",
                    "2627"
            );

            String sql = "SELECT * FROM estoque";

            Statement stm = con.createStatement();

            ResultSet rs = stm.executeQuery(sql);

            listaProdutos.clear();

            while (rs.next()) {

                Produto p = new Produto();

                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getDouble("valor"));
                p.setEstoque(rs.getInt("quantidade"));

                listaProdutos.add(p);
            }

            rs.close();
            stm.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

}
