public class ProductModal {


    private Long ID;
   private String ProductName;
    public int ProductPrice;
    public float weight;



    public Long getID() {
        return ID;
    }



    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(int productPrice) {
        ProductPrice = productPrice;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
    public void setID(Long ID) {
        this.ID = ID;
    }
}
