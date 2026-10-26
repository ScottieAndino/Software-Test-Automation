package contact;

/**
 * Represents a single contact stored in the contact service.
 * Validation rules follow the Module Three milestone requirements:
 *   - contactId: required, not null, max 10 characters, not updatable after creation
 *   - firstName: required, not null, max 10 characters
 *   - lastName: required, not null, max 10 characters
 *   - phone: required, not null, exactly 10 digits
 *   - address: required, not null, max 30 characters
 */
public class Contact {

    private final String contactId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Contact(String contactId, String firstName, String lastName, String phone, String address) {
        if (contactId == null || contactId.length() > 10) {
            throw new IllegalArgumentException("Contact ID cannot be null and cannot be longer than 10 characters");
        }
        this.contactId = contactId;
        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
        setAddress(address);
    }

    public String getContactId() {
        return contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.length() > 10) {
            throw new IllegalArgumentException("First name cannot be null and cannot be longer than 10 characters");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.length() > 10) {
            throw new IllegalArgumentException("Last name cannot be null and cannot be longer than 10 characters");
        }
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.length() != 10 || !isAllDigits(phone)) {
            throw new IllegalArgumentException("Phone number cannot be null and must be exactly 10 digits");
        }
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null || address.length() > 30) {
            throw new IllegalArgumentException("Address cannot be null and cannot be longer than 30 characters");
        }
        this.address = address;
    }

    /**
     * Simple loop based digit check, kept plain on purpose instead of using
     * streams so the logic is easy to follow.
     */
    private boolean isAllDigits(String value) {
        for (int i = 0; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
