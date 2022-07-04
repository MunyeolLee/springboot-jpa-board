package com.demo.board.dbconn;

import com.zaxxer.hikari.HikariDataSource;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;

@SpringBootTest
public class DBConnectionTest {

    @Autowired
    HikariDataSource ds;

    @Test
    void connectionTest() throws SQLException {
        try(
            Connection con = ds.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT 'SUCCESS'")
        ) {
            String data = "";
            if( rs.next() ) {
                data = rs.getString(1);
            }

            Assertions.assertThat(data).isEqualTo("SUCCESS");
        }
    }
}
