package com.remotegroup.inventory.domain.model.services;


import java.util.List;

import com.remotegroup.inventory.domain.model.aggregates.Part;
import com.remotegroup.inventory.domain.model.aggregates.PartId;
import com.remotegroup.inventory.domain.model.aggregates.Product;
import com.remotegroup.inventory.domain.model.aggregates.ProductId;
import com.remotegroup.inventory.domain.model.commands.CreatePartCommand;
import com.remotegroup.inventory.domain.model.commands.CreateProductCommand;
import com.remotegroup.inventory.domain.model.valueobjects.SupplierId;

public interface IInventoryService {
	public abstract List<Product> getProducts();
	public abstract Product createProduct(CreateProductCommand c);
	public abstract Product updateProduct(Product p, ProductId id);
	public abstract void deleteProduct(Long id);
	public abstract Product getProduct(Long id);
	public abstract List<Part> getPartByProduct(Long id);
	
	public abstract List<Part> getParts();
	public abstract Part createPart(CreatePartCommand c);
	public abstract Part updatePart(Part p, PartId id);
	public abstract void deletePart(Long id);
	public abstract Part getPart(Long id);
	public abstract SupplierId getPartSupplier(Long id);
	
	public abstract boolean checkInventory(Long itemId);
	//public abstract void procurementRequest(BackOrderSale b);
}
