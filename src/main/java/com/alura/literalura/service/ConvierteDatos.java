package com.alura.literalura.service;

import com.alura.literalura.model.DatosAPI;
import com.alura.literalura.model.DatosBook;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos {

    private ObjectMapper objectMapper = new ObjectMapper();

    public DatosAPI obtenerDatos(String json, Class<DatosAPI> clase){
        try{
            return objectMapper.readValue(json, clase);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
