package com.example.tabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record ModelosDados(List<Dados> modelos) {
}
