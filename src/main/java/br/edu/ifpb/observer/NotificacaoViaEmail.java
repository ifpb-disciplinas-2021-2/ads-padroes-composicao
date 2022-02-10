package br.edu.ifpb.observer;

import br.edu.ifpb.domain.Compra;
import br.edu.ifpb.domain.Notificacao;

public class NotificacaoViaEmail implements Notificacao {

    public void concluida(Compra compra){
        System.out.println("Notificação foi via e-mail");
    }
}
