

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class conection {

    Connection Dbconnection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Product","root","admin");
    Statement statemant=Dbconnection.createStatement();


        public conection() throws SQLException {

        }


        public  void   CreateTable() throws SQLException {
                String drop="Drop Table if exists "+ProductTable.TABLE_NAME;
            statemant.execute(  drop);

            String CREATING_TABLE="Create Table "+ProductTable.TABLE_NAME+ " ( "+
                    ProductTable.ID+" INTEGER  NOT NULL AUTO_INCREMENT,"+
                    ProductTable.PRODUCT_NAME+" VARCHAR(255), "+
                    ProductTable.PRODUCT_WEIGHT+" Float NOT NULL,"+

                    ProductTable.PRODUCT_PRICE+" INTEGER NOT NULL, "+

                    " PRIMARY KEY (" + ProductTable.ID+" ) )";
                statemant.execute( CREATING_TABLE);
            System.out.println("Created table  "+ProductTable.TABLE_NAME);
//

        }


        public  void Insert(ProductModal product) throws SQLException {


            String inserting ="Insert into "+ProductTable.TABLE_NAME+ " ( " +
                   ProductTable.PRODUCT_NAME+","+ProductTable.PRODUCT_WEIGHT+","+
                    ProductTable.PRODUCT_PRICE+" ) "+
                    "  values ( ?,? ,?)";

            PreparedStatement preparedStmt =   Dbconnection.prepareStatement (inserting);


            preparedStmt.setString(1,product.getProductName());
            preparedStmt.setFloat(2,product.getWeight());
            preparedStmt.setInt(3,product.getProductPrice());
            preparedStmt.executeUpdate();

//            statemant.execute(  inserting);
            System.out.println("Inserted succesfuly ");




            statemant.close();
            Dbconnection.close();


        }



        public   ArrayList<ProductModal> getAlldata() throws SQLException {

            ArrayList<ProductModal> Data=new ArrayList<>();
            String sql="Select * from "+ProductTable.TABLE_NAME;
            ResultSet rs= statemant.executeQuery( sql);

            while(rs.next()){
                    ProductModal modal=new ProductModal();

                modal.setID(rs.getLong(1));
                modal.setProductName( rs.getString(2));
                modal.setWeight( rs.getFloat(3));
                modal.setProductPrice(  rs.getInt(4));

                Data.add(modal);






            }


            return Data;

        }


            public HashMap<String,Integer> getgroupeddata() throws SQLException {
                HashMap<String,Integer> grouped=new HashMap<>();
                this.getAlldata().stream().forEach(item-> {

                        if(grouped.containsKey(item.getProductName())){
                            int current=grouped.get(item.getProductName());//gats data


                             grouped.put(item.getProductName(),current+item.getProductPrice());



                        }else{
                            grouped.put(item.getProductName(),item.getProductPrice());
                        }

                        }


                );

                return grouped;  }


















       public void DeleteProduct(Long Id) throws SQLException {
           String sql="Delete from "+ProductTable.TABLE_NAME+" Where "+ProductTable.ID+"=?";
           PreparedStatement statement=Dbconnection.prepareStatement(sql);

           statement.setLong(1,Id);
           statement.executeUpdate();
           System.out.println("Product with id "+Id+"Succesfully deleted");










       }


       public void UpdatePrice(Long Id,Integer newprice) throws SQLException {

            String sql="Update "+ProductTable.TABLE_NAME+" Set "+ProductTable.PRODUCT_PRICE+" = ? "+" where "+ProductTable.ID+" =? ";


            PreparedStatement statement=Dbconnection.prepareStatement(sql);

           statement.setInt(1,newprice);
           statement.setLong(2,Id);
           statement.executeUpdate();
           System.out.println("Product with Id "+Id+" Price updated to "+newprice);



       }


}
