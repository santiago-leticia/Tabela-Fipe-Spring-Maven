package com.example.tabelaFipe.commands;

import com.example.tabelaFipe.model.Dados;
import com.example.tabelaFipe.model.ModelosDados;
import com.example.tabelaFipe.model.Veiculo;
import com.example.tabelaFipe.service.ConsumoApi;
import com.example.tabelaFipe.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Commands {
    Scanner leitura = new Scanner(System.in);
    private final ConsumoApi consumo= new ConsumoApi();
    private final ConverteDados converte = new ConverteDados();


    public void opcaoDeVerMarca(String endereco, String json){
        System.out.println("Informe o código da marca para consulta ");
        var nomeMarca =leitura.nextLine();
        endereco = endereco+"/" +nomeMarca+"/modelos";
        json = consumo.obterDados(endereco);
        var modeloLista = converte.obterDados(json, ModelosDados.class);

        System.out.println("\nModelos dessa marca: ");
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);
    }

    public void nomeDoModelo(String endereco, String json){
        System.out.print("Digite um techo do nome do carro que voce gostaria de procurar");
        var nomeVeiculo = leitura.nextLine();
        var modeloLista = converte.obterDados(json, ModelosDados.class);
        List<Dados> modelosFiltrados = modeloLista.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("\nModelos filtrados");
        modelosFiltrados.forEach(System.out::println);

        System.out.println("Digite por favor o codigo do modelo para buscar os valores de avaliacao: ");
        var codigoModelo = leitura.nextLine();

        endereco = endereco+"/"+codigoModelo+"/anos";

        json= consumo.obterDados(endereco);
        List<Dados> anos = converte.obterLista(json, Dados.class);
        //vai mostra as avaliacoes por ano vai chegar varias informacoes
        //Lista de veiculos
        List<Veiculo> veiculos = new ArrayList<>();

        for (int i = 0; i < anos.size(); i++) {
            var enderecoAnos = endereco+"/"+anos.get(i).codigo();
            json = consumo.obterDados(enderecoAnos);
            Veiculo veiculo = converte.obterDados(json, Veiculo.class);
            veiculos.add(veiculo);
        }

        System.out.println("\nTodos os veiculos filtrados com avaliações por anos: ");
        veiculos.forEach(System.out::println);
    }
}
