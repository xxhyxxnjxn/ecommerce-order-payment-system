package imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PaymentStatusConverter implements AttributeConverter<PaymentStatus, String> {
    @Override
    public String convertToDatabaseColumn(PaymentStatus paymentStatus) {
        return paymentStatus.name();
    }

    @Override
    public PaymentStatus convertToEntityAttribute(String s) {
        return null;
    }
}
