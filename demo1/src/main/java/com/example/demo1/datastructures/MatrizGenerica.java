package com.example.demo1.datastructures;

import java.util.HashMap;
import java.util.Map;

public class MatrizGenerica<K, V> {
    private Map<K, Map<K, V>> matriz;

    public MatrizGenerica() {
        matriz = new HashMap<>();
    }

    public void setValor(K fila, K columna, V valor) {
        if (!matriz.containsKey(fila)) {
            matriz.put(fila, new HashMap<>());
        }
        matriz.get(fila).put(columna, valor);
    }

    public V getValor(K fila, K columna) {
        if (matriz.containsKey(fila)) {
            Map<K, V> filaMap = matriz.get(fila);
            if (filaMap.containsKey(columna)) {
                return filaMap.get(columna);
            }
        }
        return null;
    }
}