package contact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the ContactService class. Covers all three service
 * requirements: add with a unique ID, delete per contact ID, and update
 * per contact ID (firstName, lastName, phone, address).
 */
public class ContactServiceTest {

    private ContactService service;

    @BeforeEach
    public void setUp() {
        service = new ContactService();
    }

    // add requirement
    @Test
    public void testAddContact() {
        Contact contact = new Contact("1234567890", "John", "Smith", "1234567890", "123 Main Street");
        service.addContact(contact);
        assertEquals(contact, service.getContact("1234567890"));
    }

    @Test
    public void testAddContactWithDuplicateIdThrows() {
        Contact contact1 = new Contact("1234567890", "John", "Smith", "1234567890", "123 Main Street");
        Contact contact2 = new Contact("1234567890", "Jane", "Doe", "0987654321", "456 Oak Street");
        service.addContact(contact1);
        assertThrows(IllegalArgumentException.class, () -> service.addContact(contact2));
        // the original contact should still be the one stored
        assertEquals(contact1, service.getContact("1234567890"));
    }

    @Test
    public void testAddNullContactThrows() {
        assertThrows(IllegalArgumentException.class, () -> service.addContact(null));
    }

    @Test
    public void testAddMultipleContactsWithUniqueIds() {
        Contact contact1 = new Contact("1111111111", "John", "Smith", "1234567890", "123 Main Street");
        Contact contact2 = new Contact("2222222222", "Jane", "Doe", "0987654321", "456 Oak Street");
        service.addContact(contact1);
        service.addContact(contact2);
        assertEquals(contact1, service.getContact("1111111111"));
        assertEquals(contact2, service.getContact("2222222222"));
    }

    // delete requirement
    @Test
    public void testDeleteContact() {
        Contact contact = new Contact("1234567890", "John", "Smith", "1234567890", "123 Main Street");
        service.addContact(contact);
        service.deleteContact("1234567890");
        assertThrows(IllegalArgumentException.class, () -> service.getContact("1234567890"));
    }

    @Test
    public void testDeleteContactOnlyRemovesThatContact() {
        Contact contact1 = new Contact("1111111111", "John", "Smith", "1234567890", "123 Main Street");
        Contact contact2 = new Contact("2222222222", "Jane", "Doe", "0987654321", "456 Oak Street");
        service.addContact(contact1);
        service.addContact(contact2);
        service.deleteContact("1111111111");
        assertEquals(contact2, service.getContact("2222222222"));
    }

    @Test
    public void testDeleteContactNotFoundThrows() {
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("1234567890"));
    }

    @Test
    public void testDeleteNullIdThrows() {
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact(null));
    }

    // update requirement, one test per updatable field
    @Test
    public void testUpdateFirstName() {
        service.addContact(new Contact("1234567890", "John", "Smith", "1234567890", "123 Main Street"));
        service.updateFirstName("1234567890", "Jane");
        assertEquals("Jane", service.getContact("1234567890").getFirstName());
    }

    @Test
    public void testUpdateLastName() {
        service.addContact(new Contact("1234567890", "John", "Smith", "1234567890", "123 Main Street"));
        service.updateLastName("1234567890", "Doe");
        assertEquals("Doe", service.getContact("1234567890").getLastName());
    }

    @Test
    public void testUpdatePhone() {
        service.addContact(new Contact("1234567890", "John", "Smith", "1234567890", "123 Main Street"));
        service.updatePhone("1234567890", "0987654321");
        assertEquals("0987654321", service.getContact("1234567890").getPhone());
    }

    @Test
    public void testUpdateAddress() {
        service.addContact(new Contact("1234567890", "John", "Smith", "1234567890", "123 Main Street"));
        service.updateAddress("1234567890", "456 Oak Street");
        assertEquals("456 Oak Street", service.getContact("1234567890").getAddress());
    }

    @Test
    public void testUpdatingAllFieldsDoesNotChangeContactId() {
        // requirement: the contact ID shall not be updatable
        service.addContact(new Contact("1234567890", "John", "Smith", "1234567890", "123 Main Street"));
        service.updateFirstName("1234567890", "Jane");
        service.updateLastName("1234567890", "Doe");
        service.updatePhone("1234567890", "0987654321");
        service.updateAddress("1234567890", "456 Oak Street");
        assertEquals("1234567890", service.getContact("1234567890").getContactId());
    }

    // update with a bad value should be rejected and leave the old value alone
    @Test
    public void testUpdateFirstNameTooLongThrowsAndKeepsOldValue() {
        service.addContact(new Contact("1234567890", "John", "Smith", "1234567890", "123 Main Street"));
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("1234567890", "12345678901"));
        assertEquals("John", service.getContact("1234567890").getFirstName());
    }

    @Test
    public void testUpdatePhoneWithLettersThrowsAndKeepsOldValue() {
        service.addContact(new Contact("1234567890", "John", "Smith", "1234567890", "123 Main Street"));
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("1234567890", "12345abcde"));
        assertEquals("1234567890", service.getContact("1234567890").getPhone());
    }

    // update on an ID that does not exist
    @Test
    public void testUpdateFirstNameContactNotFoundThrows() {
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("1234567890", "Jane"));
    }

    @Test
    public void testUpdateLastNameContactNotFoundThrows() {
        assertThrows(IllegalArgumentException.class, () -> service.updateLastName("1234567890", "Doe"));
    }

    @Test
    public void testUpdatePhoneContactNotFoundThrows() {
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("1234567890", "0987654321"));
    }

    @Test
    public void testUpdateAddressContactNotFoundThrows() {
        assertThrows(IllegalArgumentException.class, () -> service.updateAddress("1234567890", "456 Oak Street"));
    }
}
