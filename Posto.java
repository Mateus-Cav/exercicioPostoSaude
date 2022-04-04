package exerciciopostosaude;

import java.util.List;
import java.util.ArrayList;

public class Posto {
    
    private int idPosto;
    private String nome;
    private List<Movimento> movimentos;
    
    public Posto(int id, String nome){
        this.idPosto = id;
        this.nome = nome;
        movimentos = new ArrayList<Movimento>();
    }
    
    public void registrarMovimento(Movimento movimento){
        movimentos.add(movimento);
    }
    
    public int obterEstoque(Vacina vacina){
        
        int entrada = 0;
        int saida = 0;
        int estoque = 0;
        
        
        for(Movimento movimento: movimentos){
            if(movimento.getVacina() == vacina){
                
                if(movimento.getTipoMovimento().equals('E') || movimento.getTipoMovimento().equals('e')){
                    entrada = movimento.getQuantidade();
                }
                
                if(movimento.getTipoMovimento().equals('A') || movimento.getTipoMovimento().equals('a')){
                    saida = movimento.getQuantidade();
                }
                
                if(saida > entrada){
                    estoque = 0;
                }
                
                estoque = entrada - saida;
            }
            
        }
        
        return estoque;
    }

    public int getIdPosto() {
        return idPosto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Movimento> getListaMovimento() {
        return movimentos;
    }

    public void setListaMovimento(List<Movimento> listaMovimento) {
        this.movimentos = listaMovimento;
    }

    @Override
    public String toString() {
        return "Posto{" + "idPosto=" + idPosto + ", nome=" + nome + ", movimentos=" + movimentos + '}';
    }
    
    
    
}
