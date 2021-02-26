package com.example.AddressbookSpring;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller()
public class AddressBookController {

    public final AddressBookRepository repository;

    AddressBookController(AddressBookRepository repository) {
        this.repository = repository;
    }

    @ResponseBody
    @GetMapping("/allBooks")
    public List<AddressBook> getAllBooks() {
        return (List<AddressBook>) repository.findAll();
    }

    @ResponseBody
    @PostMapping("/createAddressBook")
    public AddressBook createAddressBook() {
        System.out.println("Addressbook Created");
        return repository.save(new AddressBook());
    }

//    @ResponseBody
//    @PostMapping("/addressBook/addBuddy/{id}")
//    public AddressBook addBuddy(@PathVariable Long id, @RequestBody BuddyInfo buddy) {
//        AddressBook book = repository.findById(id).get();
//        book.addBuddy(buddy);
//        return repository.save(book); // update the book
//    }

    @ResponseBody
    @PostMapping("/addressBook/addBuddy")
    public AddressBook addBuddy(@RequestParam(name = "addressBookId") Long addressBookId, @ModelAttribute BuddyInfo buddy) {
        AddressBook book = repository.findById(addressBookId).get();
        book.addBuddy(buddy);
        return repository.save(book); // update the book
    }

    @PostMapping("/postEndpoint")
    public String pidUserSubmit(@RequestParam(name = "myid") String myid) {
        System.out.println("*** MY ID:" + myid);
        return "addressBooks";
    }

    @ResponseBody
    @DeleteMapping("/addressBook/{addressId}/remove/{buddyId}")
    public AddressBook removeBuddy(@PathVariable Long addressId, @PathVariable Long buddyId) {
        AddressBook book = repository.findById(addressId).get();
        book.removeBuddy(buddyId);
        return repository.save(book); // update the book
    }

    @GetMapping("/addressBook/{addressId}") // map request to method
    public String listAllAddressBook(@PathVariable Long addressId, Model model) {
        AddressBook book = repository.findById(addressId).get();
        model.addAttribute("addressBook", book);
        return "addressBook";
    }

    @GetMapping("/addressBooks") // map request to method
    public String addressBook(Model model) {
        System.out.println("Address book page created");
        model.addAttribute("buddy", new BuddyInfo()); // pass to the html template
        return "addressBooks";
    }
}
