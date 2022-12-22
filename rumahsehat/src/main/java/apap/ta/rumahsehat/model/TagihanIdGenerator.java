package apap.ta.rumahsehat.model;


import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TagihanIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

        String awalan = "BILL-";
        String akhir = "";
        Connection connection = session.connection();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT COUNT(KODE) AS ID FROM TAGIHAN");
            if(rs.next()) {
                Integer id = rs.getInt(1)+1;
                akhir = id.toString();
                String generatedId = awalan + akhir;
                return generatedId;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}


