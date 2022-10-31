package com.remotegroup.sales.domain.model.aggregates;

import com.remotegroup.sales.domain.model.commands.CreateInStoreSaleCommand;
import com.remotegroup.sales.domain.model.commands.UpdateInStoreSaleCommand;
import com.remotegroup.sales.domain.model.valueobjects.ReceiptNo;

import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class InStoreSale extends Sale {
    private @Id @GeneratedValue Long id;

    @Embedded
    private StoreId storeId;
    @Embedded
    private ReceiptNo receiptNo;

    public InStoreSale () {
    	super();
    }

    public InStoreSale(CreateInStoreSaleCommand command){
        this.saleId = new SaleId(command.getSaleId());
        this.storeId = new StoreId(command.getStoreId());
        this.receiptNo = new ReceiptNo(command.getReceiptNo());
    }

    public InStoreSale updateInStoreSale(UpdateInStoreSaleCommand command) {
        this.saleId = new SaleId(command.getSaleId());
        this.storeId = new StoreId(command.getStoreId());
        this.receiptNo = new ReceiptNo(command.getReceiptNo());
        return this;
    }

    public Long getId(){
        return this.id;
    }

    public StoreId getStoreId(){
        return this.storeId;
    }

    public ReceiptNo getReceiptNo(){
        return this.receiptNo;
    }

    public void setStoreId(StoreId id) {
        this.storeId = id;
    }

    public void setReceipt(ReceiptNo newReceipt){
        this.receiptNo = newReceipt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof InStoreSale))
            return false;
            InStoreSale InStoreSale = (InStoreSale) o;
        return Objects.equals(this.storeId, InStoreSale.storeId)
        && Objects.equals(this.receiptNo, InStoreSale.receiptNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.storeId, this.receiptNo);
    }

    @Override
    public String toString() {
        return super.toString() +"InStoreSale { store id='" + this.storeId.getValue() + '\'' 
        + ", receipt='" + this.receiptNo.getValue() + '\''
         + '}';
    }
}