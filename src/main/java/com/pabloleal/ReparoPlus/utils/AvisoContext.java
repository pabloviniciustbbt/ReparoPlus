package com.pabloleal.ReparoPlus.utils;

import java.util.ArrayList;
import java.util.List;

public class AvisoContext {

    private static final ThreadLocal<List<String>> avisos = ThreadLocal.withInitial(ArrayList::new);

    public static void adicionarAviso(String aviso){
        avisos.get().add(aviso);
    }

    public static List<String> obterAvisos(){
        return new ArrayList<>(avisos.get());
    }

    public static boolean temAvisos(){
        return !avisos.get().isEmpty();
    }

    public static void limparAvisos(){
        avisos.remove();
    }

}
