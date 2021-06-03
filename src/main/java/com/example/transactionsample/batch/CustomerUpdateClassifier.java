package com.example.transactionsample.batch;

import com.example.transactionsample.model.CustomerAddressUpdate;
import com.example.transactionsample.model.CustomerContactUpdate;
import com.example.transactionsample.model.CustomerNameUpdate;
import com.example.transactionsample.model.CustomerUpdate;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.classify.Classifier;

public class CustomerUpdateClassifier implements Classifier<CustomerUpdate, ItemWriter<? super CustomerUpdate>> {

    private final JdbcBatchItemWriter<CustomerUpdate> recordType1ItemWriter;
    private final JdbcBatchItemWriter<CustomerUpdate> recordType2ItemWriter;
    private final JdbcBatchItemWriter<CustomerUpdate> recordType3ItemWriter;

    public CustomerUpdateClassifier(JdbcBatchItemWriter<CustomerUpdate> recordType1ItemWriter, JdbcBatchItemWriter<CustomerUpdate> recordType2ItemWriter, JdbcBatchItemWriter<CustomerUpdate> recordType3ItemWriter) {
        this.recordType1ItemWriter = recordType1ItemWriter;
        this.recordType2ItemWriter = recordType2ItemWriter;
        this.recordType3ItemWriter = recordType3ItemWriter;
    }
    
    @Override
    public ItemWriter<? super CustomerUpdate> classify(CustomerUpdate classifiable) {
        if(classifiable instanceof CustomerNameUpdate) {
            return recordType1ItemWriter;
        }
        else if(classifiable instanceof CustomerAddressUpdate) {
            return recordType2ItemWriter;
        }
        else if(classifiable instanceof CustomerContactUpdate) {
            return recordType3ItemWriter;
        }
        else {
            throw new IllegalArgumentException("Invalid type: " + classifiable.getClass().getCanonicalName());
        }
    }
}
