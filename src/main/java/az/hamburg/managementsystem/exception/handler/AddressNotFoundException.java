package az.hamburg.managementsystem.exception.handler;

import lombok.Getter;

import java.util.List;

//@Getter
//public class AddressNotFoundException extends RuntimeException {
//    private final List<Long> addressIds;
//
//    // Yeni constructor
//    public AddressNotFoundException(List<Long> addressIds) {
//        super("Addresses with ids " + addressIds + " not found.");
//        this.addressIds = addressIds;
//    }
//
//    // Köhnə üsul üçün dəstəkləyən constructor
//    public AddressNotFoundException(String message, String reason) {
//        super(message + ": " + reason);
//        this.addressIds = List.of(); // Boş siyahı verirəm ki, null olmasın
//    }
//}


public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(String message, String code) {
        super(message);
    }
}
