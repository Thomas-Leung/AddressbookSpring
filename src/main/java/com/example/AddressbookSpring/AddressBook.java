package com.example.AddressbookSpring;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AddressBook {

    @Id
    @GeneratedValue
    private Long id;

    public List<BuddyInfo> getBuddyInfos() {
        return buddyInfos;
    }

    //    @OneToMany(cascade = CascadeType.PERSIST)
    @OneToMany(cascade = CascadeType.ALL) // has to be all
    public List<BuddyInfo> buddyInfos;

    // Empty constructor needed for JPA
    protected AddressBook() {
        buddyInfos = new ArrayList<>();
    }

    public void addBuddy(BuddyInfo info) {
        if (info != null) {
            buddyInfos.add(info);
        }
    }

    public void removeBuddy(Long id) {
        for (int i = 0; i < buddyInfos.size(); i++) {
            if (buddyInfos.get(i).getId() == id) {
                buddyInfos.remove(i);
            }
        }
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}