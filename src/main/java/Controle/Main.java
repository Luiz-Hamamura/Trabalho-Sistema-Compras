package Controle;

import Entidade.Produto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "main")
@SessionScoped
public class Main {

    private String nome;
    private double valor;
    private int estoque;
    public void consultar(){
        
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

        } catch (Exception e) {
            e.printStackTrace();
        }
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
}