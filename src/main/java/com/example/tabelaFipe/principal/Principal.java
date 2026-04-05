package com.example.tabelaFipe.principal;

import com.example.tabelaFipe.commands.Commands;
import com.example.tabelaFipe.model.Dados;
import com.example.tabelaFipe.service.ConsumoApi;
import com.example.tabelaFipe.service.ConverteDados;

import java.util.*;

public class Principal {
    Scanner leitura = new Scanner(System.in);
    private final ConsumoApi consumo= new ConsumoApi();
    private final ConverteDados converte = new ConverteDados();
    private final Commands commands = new Commands();

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu(){
        int iEscolha=-1;
        do{
            var menu = """
                ***Opções***
                
                Carro
                
                Moto
                
                Caminhão
                
                Digite um das opções para consulat:
                """;
            System.out.println(menu);
            var opcao= leitura.nextLine();
            String endereco;

            if (opcao.toLowerCase().contains("carro")) {
                endereco = URL_BASE +"carros/marcas";
            } else if(opcao.toLowerCase().contains("not")){
                endereco = URL_BASE +"motos/marcas";
            } else{
                endereco = URL_BASE+"caminhoes/marcas";
            }
            var json = consumo.obterDados(endereco);

            System.out.println("Você ja tem algo em mente para pequisar(Sim/Não): ");
            var segundaOpcao= leitura.nextLine();

            if (segundaOpcao.contains("sim")){
                System.out.println("1 - por codigo\n2 - por nome");
                var terceiraOpcao = leitura.nextLine();
                if (terceiraOpcao.contains("1")){
                    commands.opcaoDeVerMarca(endereco,json);
                } else if (terceiraOpcao.contains("2")) {
                    commands.nomeDoModelo(endereco,json);
                }
            } else if (segundaOpcao.contains("não")) {

                //criar uma variavel e receber aquela respota
                System.out.println(json);

                //fazer estrutura
                var marcas = converte.obterLista(json, Dados.class);
                //exibir essa mascar para mostra a ordem do codigo
                marcas.stream()
                        .sorted(Comparator.comparing(Dados::codigo))// ordena elementos de uma coleção (lista, set) de forma funcional, retornando um novo stream ordenado sem modificar a estrutura original
                        .forEach(System.out::println);//vai ordenar por for

            }else {
                System.out.println("Opcao invalida");
            }
            System.out.println("Gostaria de continuar (0 ou 1): ");
            iEscolha = leitura.nextInt();

        }while (iEscolha!=0);
    }
}
