package ua.kiev.prog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContactService {
    private final ContactRepository contactRepository;
    private final GroupRepository groupRepository;

    public ContactService(ContactRepository contactRepository, GroupRepository groupRepository) {
        this.contactRepository = contactRepository;
        this.groupRepository = groupRepository;
    }

    @Transactional
    public void addContact(Contact contact) {
        contactRepository.save(contact);
    }

    @Transactional
    public void addGroup(Group group) {
        groupRepository.save(group);
    }

    @Transactional
    public void deleteContacts(long[] idList) {
        for (long id : idList)
            contactRepository.deleteById(id);
    }

    //Used to remove contacts and groups, the Default group can also be removed
    @Transactional
    public void deleteGroup(Group group) {
        List<Contact> contacts;
        if (group == null) {
            contacts = groupRepository.findDefaultGroup();
        } else {
            contacts = groupRepository.findByGroup(group);
            groupRepository.delete(group);
        }
        for (Contact c : contacts) {
            contactRepository.deleteById(c.getId());
        }
    }

//    public void deleteGroup(Group group){
//        groupRepository.delete(group);
//    }

    @Transactional(readOnly = true)
    public List<Group> findGroups() {
        return groupRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Contact> findAll(Pageable pageable) {
        return contactRepository.findAll(pageable).getContent();
    }

    @Transactional(readOnly = true)
    public List<Contact> findByGroup(Group group, Pageable pageable) {
        return contactRepository.findByGroup(group, pageable);
    }

    @Transactional(readOnly = true)
    public long countByGroup(Group group) {
        return contactRepository.countByGroup(group);
    }

    @Transactional(readOnly = true)
    public List<Contact> findByPattern(String pattern, Pageable pageable) {
        return contactRepository.findByPattern(pattern, pageable);
    }

    @Transactional(readOnly = true)
    public long count() {
        return contactRepository.count();
    }

    @Transactional(readOnly = true)
    public Group findGroup(long id) {
        return groupRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Contact> findContact(Group group) {
        return groupRepository.findByGroup(group);
    }

    public void deleteContacts(List<Contact> contacts) {
        contactRepository.deleteAll(contacts);
    }

    @Transactional
    public void reset() {
        groupRepository.deleteAll();
        contactRepository.deleteAll();

        Group group = new Group("Test");
        Contact contact;

        addGroup(group);

        for (int i = 0; i < 13; i++) {
            contact = new Contact(null, "Name" + i, "Surname" + i, "1234567" + i, "user" + i + "@test.com");
            addContact(contact);
        }
        for (int i = 0; i < 10; i++) {
            contact = new Contact(group, "Other" + i, "OtherSurname" + i, "7654321" + i, "user" + i + "@other.com");
            addContact(contact);
        }
    }
}
