package imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.repository;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.TransactionType;

public interface TransactionTypeRepository {
    TransactionType save(TransactionType transactionType);
}
