package com.remotegroup.shareddomain.model.services;


import java.util.List;

import com.remotegroup.shareddomain.model.aggregates.Part;
import com.remotegroup.shareddomain.model.aggregates.PartId;
import com.remotegroup.shareddomain.model.aggregates.Product;
import com.remotegroup.shareddomain.model.aggregates.ProductId;
import com.remotegroup.shareddomain.model.commands.CreatePartCommand;
import com.remotegroup.shareddomain.model.commands.CreateProductCommand;
import com.remotegroup.shareddomain.model.commands.UpdatePartCommand;
import com.remotegroup.shareddomain.model.commands.UpdateProductCommand;
import com.remotegroup.shareddomain.model.valueobjects.SupplierId;

public interface IInventoryService {
	public abstract List<Product> getProducts();
	public abstract Product createProduct(CreateProductCommand c);
	public abstract Product updateProduct(UpdateProductCommand c);
	public abstract void deleteProduct(ProductId id);
	public abstract Product getProduct(ProductId id);
	//public abstract List<Part> getPartByProduct(ProductId id);
	
	public abstract List<Part> getParts();
	public abstract Part createPart(CreatePartCommand c);
	public abstract Part updatePart(UpdatePartCommand c);
	public abstract void deletePart(PartId id);
	public abstract Part getPart(PartId id);
	public abstract SupplierId getPartSupplier(PartId id);
	
	public abstract boolean checkInventory(ProductId itemId);
	//public abstract void procurementRequest(BackOrderSale b);
	List<ProductId> getProductIds();
}
