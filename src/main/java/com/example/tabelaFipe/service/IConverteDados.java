package com.example.tabelaFipe.service;

import java.util.List;

public interface IConverteDados {
    //converta dados para uma classe
    <T> T obterDados(String json, Class<T> classe);

    <T> List<T> obterLista(String json,Class<T> tClass);
}
