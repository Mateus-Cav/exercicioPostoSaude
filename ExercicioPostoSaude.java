package exerciciopostosaude;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class ExercicioPostoSaude {

    static Scanner ler = new Scanner(System.in);
    static List<Posto> listaPosto = new ArrayList<Posto>();
    static List<Vacina> listaVacina = new ArrayList<Vacina>();
    
    public static void main(String[] args) throws ParseException {
        
        Posto postoUm = new Posto(1, "Posto UNIT");
        Posto postoDois = new Posto(2, "Posto UFPE");
        
        listaPosto.add(postoUm);
        listaPosto.add(postoDois);       
        
        Vacina vacinaCovid = new Vacina(1, "Covid-19", 'E');
        Vacina vacinaGripe = new Vacina(2, "Gripe", 'N');
        Vacina vacinaTetano = new Vacina(3, "Tétano", 'N');
        Vacina vacinaMalaria = new Vacina(4, "Malária", 'N');
        Vacina vacinaFebreAmarela = new Vacina(5, "Febre Amarela", 'E');
        
        listaVacina.add(vacinaCovid);
        listaVacina.add(vacinaGripe);
        listaVacina.add(vacinaTetano);
        listaVacina.add(vacinaMalaria);
        listaVacina.add(vacinaFebreAmarela);
                
        while(true){
            
            System.out.println("### Sistema de vacinação UNIT ###");
            System.out.println("Digite [1] para registrar uma movimentação de vacina");
            System.out.println("Digite [2] para consultar repasse de vacinas");
            System.out.println("Digite [3] para consultar estoque");
            System.out.println("Digite [0] para encerrar o programa");
            
            int opcao = ler.nextInt();
            
            switch(opcao){
                
                case 1:
                    registrarMovimentacao();
                    break;
                    
                case 2:
                    consultarRepasse();
                    break;
                    
                case 3:
                    consultarEstoque();
                    break;
                    
                case 0:
                    System.out.println("Encerrando o programa...");
                    return;
                    
                default:
                    System.out.println("Opção incorreta. Tente novamente!");
                    break;
                
            }
            
        }
        
        
    }
    
public static void registrarMovimentacao() throws ParseException{
    
    while(true){
        
        System.out.println("### REGISTRO DE MOVIMENTAÇÃO DE VACINAS ###\n");
        System.out.println("Digite [0] se quiser retornar ao Menu\n");
        
        System.out.println("Informe o Id do posto que vai registrar a movimentação de vacinas: ");
        int idPosto = ler.nextInt();
        
        if(idPosto == 0){
            System.out.println("Retornando ao menu");
            return;
        }
        
        Posto postoEncontrado = pesquisarPosto(idPosto);
        
        if(postoEncontrado == null){
            System.out.println("Posto não cadastrado!\n");
            continue;
        }
        
        else{
            System.out.println("\nInforme o Id da vacina que será movimentada: ");
            int idVacina = ler.nextInt();
            
            Vacina vacinaEncontrada = pesquisarVacina(idVacina);
            
            if(vacinaEncontrada == null){
                System.out.println("Vacina não cadastrada!\n");
                continue;
            }
            
            else{
                    
                System.out.println("\nInforme se a movimentação é do tipo Aplicação ou Entrada (A ou E): ");
                Character tipoMovimentacao = ler.next().charAt(0);
                
                if(tipoMovimentacao == 'A' || tipoMovimentacao == 'E' || 
                   tipoMovimentacao == 'a' || tipoMovimentacao == 'e'){
                    
                    ler.nextLine();
                    System.out.println("\nInforme a data da movimentação: (dd/mm/aaa)");
                    String dataRecebida = ler.nextLine();

                    SimpleDateFormat dataParaFormatar = new SimpleDateFormat("dd/MM/yyyy");
                    Date dataFormatada = dataParaFormatar.parse(dataRecebida);

                    System.out.println("\nInforme a quantidade de vacinas da movimentação: ");
                    int quantidade = ler.nextInt();
                    
                    Movimento movimento = new Movimento(dataFormatada, tipoMovimentacao, vacinaEncontrada ,quantidade);
                    
                    postoEncontrado.registrarMovimento(movimento);  
                    
                    //postoEncontrado.getListaMovimento().add(movimento);
                    System.out.println("\nMovimentação registrada com sucesso!");
                }
                else{
                    System.out.println("\nTipo de vacina inválido!");
                    continue;
                }
            }
        }
        
    }
    
}
    
public static void consultarRepasse(){
    
    while(true){
        System.out.println("### CONSULTA DE REPASSE DE VACINAS ###\n");
        System.out.println("Digite [0] se quiser retornar ao Menu\n");
        System.out.println("Informe o Id do posto que será consultado: \n");
        int idPosto = ler.nextInt();
        
        if(idPosto == 0){
            System.out.println("Retornando ao Menu...\n");
            return;
        }
        
        Posto postoEncontrado = pesquisarPosto(idPosto);
        
        if(postoEncontrado == null){
            System.out.println("POSTO NÃO CADASTRADO!\n");
            return;
        }
        
        else{
            double valorTotal = 0;
            
            for(Movimento movimento: postoEncontrado.getListaMovimento()){
                if(movimento.getTipoMovimento().equals('A') || movimento.getTipoMovimento().equals('a')){
                    System.out.println("1 - Id do Posto: " + postoEncontrado.getIdPosto());
                    System.out.println("2 - Nome do Posto:" + postoEncontrado.getNome());
                    System.out.println("3 - Id da Movimentação: " + movimento.getIdMovimento());
                    System.out.println("4 - Data da Movimentação: " + movimento.getData());
                    System.out.println("5 - Quantidade de doses: " + movimento.getQuantidade());
                    System.out.println("6 - Valor do repasse R$: " + movimento.calculaRepasse());
                    System.out.println("------------------------------------------------");
                    
                    valorTotal = valorTotal + (movimento.calculaRepasse());
                }
            }
            System.out.println("Valor total do repasse: R$" + valorTotal);
            System.out.println("------------------------------------------------");
            
        }
    }
    
}

public static void consultarEstoque(){
    
    while(true){
        System.out.println("### CONSULTA DE ESTOQUE DE VACINAS ###\n");
        System.out.println("Digite [0] se quiser retornar ao Menu\n");
        System.out.println("Informe o Id do posto que será consultado: \n");
        int idPosto = ler.nextInt();
        
        if(idPosto == 0){
            System.out.println("Retornando ao Menu...\n");
            return;
        }
        
        Posto postoEncontrado = pesquisarPosto(idPosto);
        
        if(postoEncontrado == null){
            System.out.println("POSTO NÃO CADASTRADO!\n");
            return;
        }
        
        else{
            int totalDeDoses = 0;
            for(Movimento movimentos: postoEncontrado.getListaMovimento()){
                System.out.println("1 - Id do Posto: " + postoEncontrado.getIdPosto());
                System.out.println("2 - Nome do Posto: " + postoEncontrado.getNome());
                System.out.println("3 - Id de Vacina movimentada no posto: " + movimentos.getVacina().getIdVacina());
                System.out.println("4 - Descrição de Vacina movimentada no posto: " + movimentos.getVacina().getDescricao());
                System.out.println("5 - Tipo de Vacina movimentada no posto: " + movimentos.getVacina().getTipo());
                System.out.println("6 - Estoque de Vacina do posto: " + postoEncontrado.obterEstoque(movimentos.getVacina()));
                System.out.println("----------------------------------------------------------");
                
                totalDeDoses = totalDeDoses + movimentos.getQuantidade();
                
            }
            
            System.out.println("Total de doses do posto: " + totalDeDoses);
        }
    }
}

public static Posto pesquisarPosto(int id){
    for(Posto postoPesquisado: listaPosto){
        if(postoPesquisado.getIdPosto() == id){
            return postoPesquisado;
        }
    }
    
    return null;
}

public static Vacina pesquisarVacina(int id){
    for(Vacina vacinaPesquisada: listaVacina){
        if(vacinaPesquisada.getIdVacina() == id){
            return vacinaPesquisada;
        }
    }
    
    return null;
}

}
