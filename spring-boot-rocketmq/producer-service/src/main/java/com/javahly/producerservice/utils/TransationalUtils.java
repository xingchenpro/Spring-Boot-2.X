package com.javahly.producerservice.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

@Service
public class TransationalUtils {
    @Autowired
    public DataSourceTransactionManager transactionManager;

    public TransactionStatus begin() {
        TransactionStatus transaction = transactionManager.getTransaction(new DefaultTransactionAttribute());
        return transaction;
    }

    public void commit(TransactionStatus transaction) {
        transactionManager.commit(transaction);

    }

    public void rollback(TransactionStatus transaction) {
        transactionManager.rollback(transaction);
    }

}
