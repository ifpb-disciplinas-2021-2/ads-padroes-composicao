package br.edu.ifpb.main;

import br.edu.ifpb.bridge.CompraAPrazo;
import br.edu.ifpb.bridge.CompraAVista;
import br.edu.ifpb.domain.Compra;
import br.edu.ifpb.domain.Entrega;
import br.edu.ifpb.domain.ItemDeVenda;
import br.edu.ifpb.domain.Produto;
import br.edu.ifpb.observer.NotificacaoNoConsole;
import br.edu.ifpb.observer.NotificacaoViaEmail;
import br.edu.ifpb.observer.NotificacaoViaSMS;
import br.edu.ifpb.strategy.EntregaPorCorreios;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 29/06/2021, 10:57:59
 */
public class Contexto {

    public static void main(String[] args) {
        Produto arroz = new Produto(
            "arroz",4.50
        );
        Produto leite = new Produto(
            "leite",5.00
        );
        Produto cafe = new Produto(
            "cafe",3.50
        );

        Entrega entrega = new EntregaPorCorreios();
        Compra compra = new CompraAPrazo(entrega);

        compra.addNotificador(new NotificacaoNoConsole());
        compra.addNotificador(new NotificacaoViaSMS());
        compra.addNotificador(new NotificacaoViaEmail());

        compra.adicionar(
                new ItemDeVenda(2, cafe)
        );
        compra.concluir();

    }
}
