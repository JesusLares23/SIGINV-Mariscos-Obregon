/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dto_mariscos;

import java.time.Instant;
import org.bson.types.ObjectId;


/**
 *
 * @author Cesar Demian Quiroz Montijo
 */
public class Inventario {
    private ObjectId _id;
    //Lista del inventario de insumos
    private Insumo insumo;
    //Stock de cuanto se registro
    private Double stockInicial;
    //Stock actual del insumo
    private Double stockActual;
    //Stock minimo para marcarlo como stock bajo
    private Double stockMinimo;
    
    private Instant createdAt;
    
    private Instant updatedAt;

    public Inventario() {
    }
    
    // StockMinimo no se pasa como parametro en el constructor ya que se calcula mediante el tipo de unidadMedida

    public Inventario(ObjectId _id, Insumo insumo, Double stockInicial, Double stockActual, Instant createdAt, Instant updatedAt) {
        this._id = _id;
        this.insumo = insumo;
        this.stockInicial = stockInicial;
        this.stockActual = stockActual;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public Double getStockInicial() {
        return stockInicial;
    }

    public void setStockInicial(Double stockInicial) {
        this.stockInicial = stockInicial;
    }

    public Double getStockActual() {
        return stockActual;
    }

    public void setStockActual(Double stockActual) {
        this.stockActual = stockActual;
    }

    public Double getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Double stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    

    
    
    
    /**
     * Calcula el Stock minimo mediante el tipo de unidadMedida.
     * 
     * @throws IllegalArgumentException Si el insumo es nulo, la unidad de medida
     *                                  esta vacia, o no coincide con ninguna unidad
     *                                  conocida.
     */
    private void calcularStockMinimo(){
        
        if(this.getInsumo() == null){
            throw new IllegalArgumentException("El insumo no puede ser nulo.");
        }
        
        String unidadMedida = this.getInsumo().getUnidadMedida();
        
        if(unidadMedida == null || unidadMedida.isBlank()){
            throw new IllegalArgumentException("La unidad de Medida no puede estar vacia.");
        }
        
        if(unidadMedida.equalsIgnoreCase("kg")){
            
            this.setStockMinimo(15.0);
        } else
        
        if(unidadMedida.equalsIgnoreCase("pieza")){
            
            this.setStockMinimo(30.0);
        }
        
        else if(unidadMedida.equalsIgnoreCase("paquete")){
            this.setStockMinimo(35.0);
        }
        
        else if(unidadMedida.equalsIgnoreCase("botella")){
            this.setStockMinimo(15.0);
        }
        
        else{
            throw new IllegalArgumentException("Unidad de medida no reconocida: " + unidadMedida);
        }

    }
    
    /**
    * Formatea un valor de stock segun la unidad de medida del insumo.
    * Para kg devuelve con decimales, para el resto como entero.
    *
    * @param valor El valor de stock a formatear
    * @return String con el valor formateado
    */
   public String formatearStock(Double valor) {
       if (valor == null) return "0";

       String unidad = this.getInsumo().getUnidadMedida();

       if (unidad.equalsIgnoreCase("kg")) {
           return valor + " kg";
       } else if(stockActual > 1){
           return valor.intValue() + " " + unidad + "s";
       } else{
           return valor.intValue() + " " + unidad;
       }
   }
    

    @Override
    public String toString() {
        return "Inventario{" + "insumo=" + insumo + ", stockInicial=" + stockInicial + ", stockActual=" + stockActual + ", stockMinimo=" + stockMinimo + '}';
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
    
    
    
    
}
