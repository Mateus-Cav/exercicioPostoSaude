package exerciciopostosaude;

import java.util.Date;


public class Movimento {
    
    static private int ordenamento = 0;

    private int idMovimento;
    private Date data;
    private Character tipoMovimento;
    private Vacina vacina;
    private int quantidade;
    private float valorRepasse;
    
    public Movimento(Date data, Character tipo, Vacina vacina, int quantidade){
        this.idMovimento = ++ordenamento;
        this.data = data;
        this.tipoMovimento = tipo;
        this.vacina = vacina;
        this.quantidade = quantidade;
        this.calculaRepasse();
    }
    
    public double calculaRepasse(){
        
        double repasse = 0f;
        
        if(this.tipoMovimento.equals('A') || this.tipoMovimento.equals('a')){
            
            if(vacina.getTipo().equals('N') || vacina.getTipo().equals('n')){
                repasse = (this.quantidade / 100) * 30;
            }
            
            else if(vacina.getTipo().equals('E') || vacina.getTipo().equals('e')){
                repasse = (this.quantidade / 100) * 40;
            }
            
        }
        
        
        return repasse;
        
    }

    public int getIdMovimento() {
        return idMovimento;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Character getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(Character tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public Vacina getVacina() {
        return vacina;
    }

    public void setVacina(Vacina vacina) {
        this.vacina = vacina;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getValorRepasse() {
        return valorRepasse;
    }

    @Override
    public String toString() {
        return "Movimento{" + "idMovimento=" + idMovimento + ", data=" + data + 
                ", tipoMovimento=" + tipoMovimento + ", vacina=" + vacina + ", "
                + "quantidade=" + quantidade + ", valorRepasse=" + valorRepasse + '}';
    }

    
    
}
