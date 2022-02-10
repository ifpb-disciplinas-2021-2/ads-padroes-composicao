package br.edu.ifpb.domain;

public class Desconto {
    public void aplicar(Compra compra){
        if(compra.quantidade() > 10){
            compra.adicionarDesconto(10); //absoluto
        }
        if(compra.naBlackFriday()){
            double valorTotal = compra.valorTotal();
            double desconto = 0.3 * valorTotal; //30%
            compra.adicionarDesconto(desconto);
        }
    }
}
