package com.example.AddressbookSpring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller()
public class AddressBookSPAController {

    public final AddressBookRepository repository;

    AddressBookSPAController(AddressBookRepository repository) {
        this.repository = repository;
    }

//    @ResponseBody
//    @GetMapping("/allBooks")
//    public List<AddressBook> getAllBooks() {
//        return (List<AddressBook>) repository.findAll();
//    }
//
    @ResponseBody
    @PostMapping("/spa/createAddressBook")
    public AddressBook createAddressBook() {
        System.out.println("Addressbook Created");
        return repository.save(new AddressBook());
    }

    @ResponseBody
    @PostMapping("/spa/addressBook/addBuddy/{id}")
    public AddressBook addBuddy(@PathVariable Long id, @RequestBody BuddyInfo buddy) {
        AddressBook book = repository.findById(id).get();
        book.addBuddy(buddy);
        return repository.save(book); // update the book
    }

    @PostMapping("/spa/postID")
    public String pidUserSubmit(@RequestParam(name = "myid") String myid) {
        System.out.println("*** SPA ID:" + myid);
        return "addressBooks";
    }

    @ResponseBody
    @GetMapping("/spa/addressBook/{addressId}") // map request to method
    public AddressBook listAllAddressBook(@PathVariable Long addressId) {
        AddressBook book = repository.findById(addressId).get();
        return book;
    }

    @GetMapping("/spa/addressBooks") // map request to method
    public String addressBook(Model model) {
        System.out.println("Address book page created");
        model.addAttribute("buddy", new BuddyInfo()); // pass to the html template
        return "index";
    }
}
