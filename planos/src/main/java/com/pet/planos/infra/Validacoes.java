package com.pet.planos.infra;

public class Validacoes extends RuntimeException{
      public Validacoes(String mensagem){
          super(mensagem);
      }
}
