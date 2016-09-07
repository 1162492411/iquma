package com.iquma.pojo;

public class Weight extends WeightKey {
    private Byte typeweight;

    public Byte gettypeweight() {
        return typeweight;
    }

    public void settypeweight(Byte typeweight) {
        this.typeweight = typeweight;
    }
    
    public String toString(){
    	return super.toString() + ",类别权重 : " + typeweight + "]";
    }
}