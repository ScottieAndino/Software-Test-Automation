package contact;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the Contact class. Each of the five Contact class
 * requirements (contact ID, firstName, lastName, phone, address) gets a
 * valid case, an invalid case, and a boundary case where a limit exists.
 *
 * Note on "contact ID shall not be updatable": the contactId field is final
 * and Contact has no setContactId method, so there is nothing to call that
 * could change it after the object is created.
 */
public class ContactTest {

    @Test
    public void testValidContactIsCreated() {
        Contact contact = new Contact("1234567890", "John", "Smith", "1234567890", "123 Main Street");
        assertEquals("1234567890", contact.getContactId());
        assertEquals("John", contact.getFirstName());
        assertEquals("Smith", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("123 Main Street", contact.getAddress());
    }

    // contact ID requirement
    @Test
    public void testContactIdCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact(null, "John", "Smith", "1234567890", "123 Main Street"));
    }

    @Test
    public void testContactIdCannotExceedTenCharacters() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("12345678901", "John", "Smith", "1234567890", "123 Main Street"));
    }

    @Test
    public void testContactIdAtTenCharactersIsAccepted() {
        // boundary case: exactly 10 characters should be accepted
        Contact contact = new Contact("1234567890", "John", "Smith", "1234567890", "123 Main Street");
        assertEquals("1234567890", contact.getContactId());
    }

    @Test
    public void testContactIdIsNotChangedByUpdatingOtherFields() {
        // requirement: the contact ID shall not be updatable, so changing every
        // other field must leave the ID exactly as it was
        Contact contact = new Contact("1234567890", "John", "Smith", "1234567890", "123 Main Street");
        contact.setFirstName("Jane");
        contact.setLastName("Doe");
        contact.setPhone("0987654321");
        contact.setAddress("456 Oak Street");
        assertEquals("1234567890", contact.getContactId());
    }

    // firstName requirement
    @Test
    public void testFirstNameCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("1234567890", null, "Smith", "1234567890", "123 Main Street"));
    }

    @Test
    public void testFirstNameCannotExceedTenCharacters() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("1234567890", "12345678901", "Smith", "1234567890", "123 Main Street"));
    }

    @Test
    public void testFirstNameAtTenCharactersIsAccepted() {
        // boundary case: exactly 10 characters should be accepted
        Contact contact = new Contact("1234567890", "1234567890", "Smith", "1234567890", "123 Main Street");
        assertEquals("1234567890", contact.getFirstName());
    }

    // lastName requirement
    @Test
    public void testLastNameCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("1234567890", "John", null, "1234567890", "123 Main Street"));
    }

    @Test
    public void testLastNameCannotExceedTenCharacters() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("1234567890", "John", "12345678901", "1234567890", "123 Main Street"));
    }

    @Test
    public void testLastNameAtTenCharactersIsAccepted() {
        // boundary case: exactly 10 characters should be accepted
        Contact contact = new Contact("1234567890", "John", "1234567890", "1234567890", "123 Main Street");
        assertEquals("1234567890", contact.getLastName());
    }

    // phone requirement
    @Test
    public void testPhoneCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("1234567890", "John", "Smith", null, "123 Main Street"));
    }

    @Test
    public void testPhoneCannotBeShorterThanTenDigits() {
        // 9 digits, one under the exact length
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("1234567890", "John", "Smith", "123456789", "123 Main Street"));
    }

    @Test
    public void testPhoneCannotBeLongerThanTenDigits() {
        // 11 digits, one over the exact length
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("1234567890", "John", "Smith", "12345678901", "123 Main Street"));
    }

    @Test
    public void testPhoneCannotContainLetters() {
        // right length (10) but not all digits
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("1234567890", "John", "Smith", "12345abcde", "123 Main Street"));
    }

    @Test
    public void testPhoneAtTenDigitsIsAccepted() {
        // boundary case: exactly 10 digits should be accepted
        Contact contact = new Contact("1234567890", "John", "Smith", "0987654321", "123 Main Street");
        assertEquals("0987654321", contact.getPhone());
    }

    // address requirement
    @Test
    public void testAddressCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("1234567890", "John", "Smith", "1234567890", null));
    }

    @Test
    public void testAddressCannotExceedThirtyCharacters() {
        // 31 characters, one over the limit
        String thirtyOneCharacters = "1234567890123456789012345678901";
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("1234567890", "John", "Smith", "1234567890", thirtyOneCharacters));
    }

    @Test
    public void testAddressAtThirtyCharactersIsAccepted() {
        // boundary case: exactly 30 characters should be accepted
        String thirtyCharacters = "123456789012345678901234567890";
        Contact contact = new Contact("1234567890", "John", "Smith", "1234567890", thirtyCharacters);
        assertEquals(thirtyCharacters, contact.getAddress());
    }
}
