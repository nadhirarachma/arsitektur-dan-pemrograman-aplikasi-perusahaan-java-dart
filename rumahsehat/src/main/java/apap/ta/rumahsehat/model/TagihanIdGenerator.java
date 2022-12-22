package apap.ta.rumahsehat.model;


import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TagihanIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

        var awalan = "BILL-";
        var akhir = "";
        var connection = session.connection();

        try (var statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT COUNT(KODE) AS ID FROM TAGIHAN");
            if(rs.next()) {
                Integer id = rs.getInt(1)+1;
                akhir = id.toString();
                return awalan + akhir;
            }
        }
        catch (SQLException e){
            log.error(e.getMessage());
        }
        return null;
    }
}


