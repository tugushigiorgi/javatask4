import java.sql.SQLException;

public class Main {


    public static void main(String[] args) throws SQLException {

        conection db = new conection();




       db.CreateTable();


        ProductModal  Strawberry= new ProductModal();

        Strawberry.setProductName("Strawberry");
        Strawberry.setWeight(1.2f);
        Strawberry.setProductPrice(34);
        db.Insert (Strawberry);

        ProductModal  apple= new ProductModal();

        apple.setProductName("    apple");
        apple.setWeight(1.2f);
        apple.setProductPrice(34);
        db.Insert (  apple);

   db.DeleteProduct(2L);

    db.UpdatePrice(3L,16);

    //gets all data
   db.getAlldata().stream().forEach(item-> System.out.println(item.getProductName()));

            //returns hashmap   Stream api
        System.out.println(db.getgroupeddata());

        db.statemant.close();
        db.Dbconnection.close();

    }
}
