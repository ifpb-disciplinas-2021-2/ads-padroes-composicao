package br.edu.ifpb.domain;

import br.edu.ifpb.observer.NotificacaoNoConsole;
import br.edu.ifpb.observer.NotificacaoViaSMS;
import br.edu.ifpb.strategy.Retirada;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 29/06/2021, 10:44:37
 */
// Source
public abstract class Compra {

    private final List<Item> itens = new ArrayList<>();
    private final Entrega entrega;
    private final List<Notificacao> notificacoes = new ArrayList<>();
    private LocalDate criadaEm;
//    private String status; // CRIADA, EM_PROCESSAMENTO, CONCLUIDA -> State
    private double desconto = 0;

    public Compra() {
        this(new Retirada());
    }

    //ctor principal
    public Compra(Entrega entrega) {
        Objects.requireNonNull(entrega,"Não podemos criar uma compra sem uma entrega");
        this.entrega = entrega;
        this.criadaEm = LocalDate.now();
    }

    public void adicionar(Item item) {
//        ItemDeVenda item = new ItemDeVenda(
//            quantidade,produto
//        );
        if (!itens.contains(item)) {
            itens.add(item);
        }
    }
    public String concluir() {
        double taxa = this.entrega.calcularTaxa(this); //hook methods - > hook classes
        double valorTotal = this.valorTotal() + taxa;
        String codigoPagamento = pagamento().pagar(valorTotal); //hook methods

        notificarClientes(); // informar que a compra foi concluída
        return codigoPagamento;
    }

    protected abstract Pagamento pagamento();

//    public void notificador(){
//        Notificacao console = new NotificacaoNoConsole();
//        Notificacao sms  = new NotificacaoViaSMS();
//        console.concluida(this);
//        sms.concluida(this);
//    }
//    //notify
    private void notificarClientes() {
        notificacoes.forEach((cliente) -> {
            //notificar cliente a cliente
            cliente.concluida(this);
        });
    }

    public void addNotificador(Notificacao notificacao) {
        this.notificacoes.add(notificacao);
    }

    public double valorTotal() {
        return itens.stream()
            .mapToDouble(Item::subTotal)
            .sum() - desconto;
    }

    public double calculaTaxa() {
        return this.entrega.calcularTaxa(this);
    }
    public  void adicionarDesconto(double desconto){
        this.desconto+=desconto;
    }
    public int quantidade(){
        return this.itens.size();
    }
    public boolean naBlackFriday(){
        return criadaEm.getMonthValue()==8;
    }

}
