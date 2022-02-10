package br.edu.ifpb.observer;

import br.edu.ifpb.domain.Compra;
import br.edu.ifpb.domain.Notificacao;

public class NotificacaoNoConsole implements Notificacao {

    public void concluida(Compra compra){
        System.out.println("Notificação no Console da compra: "+compra.valorTotal());
    }
}
