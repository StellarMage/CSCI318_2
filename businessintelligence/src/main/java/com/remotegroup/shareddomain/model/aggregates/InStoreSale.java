package com.remotegroup.shareddomain.model.aggregates;

import com.remotegroup.shareddomain.model.commands.CreateInStoreSaleCommand;
import com.remotegroup.shareddomain.model.commands.UpdateInStoreSaleCommand;
import com.remotegroup.shareddomain.model.valueobjects.ReceiptNo;

import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class InStoreSale extends Sale {
    private @Id @GeneratedValue Long id;

    @Embedded
    private InStoreSaleId inStoreSaleId;
    @Embedded
    private StoreId storeId;
    @Embedded
    private ReceiptNo receiptNo;

    InStoreSale () {
    	super();
    }

    public InStoreSale(CreateInStoreSaleCommand command){
        this.inStoreSaleId = new InStoreSaleId(command.getInStoreSaleId());
        this.saleId = new SaleId(command.getSaleId());
        this.storeId = new StoreId(command.getStoreId());
        this.receiptNo = new ReceiptNo(command.getReceiptNo());
    }

    public InStoreSale updateInStoreSale(UpdateInStoreSaleCommand command) {
        this.inStoreSaleId = new InStoreSaleId(command.getInStoreSaleId());
        this.saleId = new SaleId(command.getSaleId());
        this.storeId = new StoreId(command.getStoreId());
        this.receiptNo = new ReceiptNo(command.getReceiptNo());
        return this;
    }

    public Long getId(){
        return this.id;
    }

    public InStoreSaleId getInStoreSaleId(){
        return this.inStoreSaleId;
    }

    public StoreId getStoreId(){
        return this.storeId;
    }

    public ReceiptNo getReceiptNo(){
        return this.receiptNo;
    }

    public void setInStoreSaleId(InStoreSaleId id) {
        this.inStoreSaleId = id;
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
        return Objects.equals(this.inStoreSaleId, InStoreSale.inStoreSaleId) 
        && Objects.equals(this.storeId, InStoreSale.storeId)
        && Objects.equals(this.receiptNo, InStoreSale.receiptNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.inStoreSaleId, this.storeId, this.receiptNo);
    }

    @Override
    public String toString() {
        return "InStoreSale{" + "inStoreSaleId=" + this.inStoreSaleId + '\''
        + ", store id='" + this.storeId + '\'' 
        + ", receipt='" + this.receiptNo + '\''
         + '}';
    }
}