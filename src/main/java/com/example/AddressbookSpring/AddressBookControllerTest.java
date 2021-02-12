package com.example.AddressbookSpring;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class AddressBookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressBookRepository addressBookService;

    private static List<AddressBook> addressBooks;

    @BeforeAll
    public static void setup() {
        List<BuddyInfo> list= new ArrayList<BuddyInfo>();
        list.add(new BuddyInfo("Thomas", "Home", "123"));
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy(new BuddyInfo("Thomas", "Home", "123"));
        addressBooks = new ArrayList<AddressBook>() {{add(addressBook);}};
    }

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("buddyApi")));
    }

    @Test
    public void addressBookShouldReturnMessageFromService() throws Exception {
        when(addressBookService.findAll()).thenReturn(addressBooks);
        this.mockMvc.perform(get("/allBooks")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Thomas")));
    }
}