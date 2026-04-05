package com.example.tabelaFipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.Collections;
import java.util.List;

public class ConverteDados implements IConverteDados {
    private final ObjectMapper mapper= new ObjectMapper();


    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try{
            return mapper.readValue(json, classe);
        }catch(JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> obterLista(String json, Class<T> tClass) {
        CollectionType l = mapper.getTypeFactory()
                .constructCollectionType(List.class, tClass);//informar o que vai ter
        try{
            return mapper.readValue(json, l);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
}
