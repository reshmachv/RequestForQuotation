package com.ecommerce.SupplierService.IdGenerator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomIdGenerator implements IdentifierGenerator {
    //@Override
    /*public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        *//*String prefix = "SID";
        String suffix = "";
        try {
            Connection connection = session.getJdbcConnectionAccess().obtainConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select count(supplier_id) from supplier");
            if(resultSet.next()) {
                Integer id = resultSet.getInt(1) + 1;
                suffix = id.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prefix + suffix;
    }*/

    private static final String PREFIX = "SID";
    private static final int CACHE_SIZE = 10;  // Number of IDs to cache
    private int currentValue = 0;  // Current value in the cache
    private int maxValue = 0;  // Max value fetched from DB

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) {
        if (currentValue >= maxValue) {
            // Fetch the next batch of IDs from the database
            fetchNextBatch(session);
        }

        // Increment the in-memory value and return it as part of the ID
        currentValue++;
        String suffix = String.format("%03d", currentValue);
        return PREFIX + suffix;
    }

    // Fetch the next batch of IDs from the database
    private void fetchNextBatch(SharedSessionContractImplementor session) {
        try {
            // Get a JDBC connection
            Connection connection = session.getJdbcConnectionAccess().obtainConnection();
            Statement statement = connection.createStatement();

            // Fetch the next available ID and increment it by CACHE_SIZE in the DB
            ResultSet rs = statement.executeQuery("SELECT next_val FROM id_sequence FOR UPDATE");

            if (rs.next()) {
                int nextValue = rs.getInt(1);
                currentValue = nextValue;  // Start from current next_val
                maxValue = nextValue + CACHE_SIZE - 1;  // Batch up to next_val + cache size

                // Update the sequence value in the database to the next value
                statement.executeUpdate("UPDATE id_sequence SET next_val = next_val + " + CACHE_SIZE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to fetch batch of sequence values from the database", e);
        }
    }
}
