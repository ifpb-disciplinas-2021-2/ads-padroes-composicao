package br.edu.ifpb.bridge;

import br.edu.ifpb.domain.Compra;
import br.edu.ifpb.domain.Entrega;
import br.edu.ifpb.domain.Pagamento;
import br.edu.ifpb.factory.PagamentoPorCartaoDeCredito;

public class CompraAPrazo extends Compra {
    public CompraAPrazo(Entrega entrega){
        super(entrega);
    }
    @Override
    protected Pagamento pagamento() {
        return new PagamentoPorCartaoDeCredito();
    }
}
