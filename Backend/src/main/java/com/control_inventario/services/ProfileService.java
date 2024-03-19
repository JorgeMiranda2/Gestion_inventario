package com.control_inventario.services;

import com.control_inventario.Models.Profile;
import com.control_inventario.Repositories.IProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private IProfile profileRepo;

    public List<Profile> list() {
        return profileRepo.findAll();
    }

    public Optional<Profile> getProfileById(Long id) {
        return profileRepo.findById(id);
    }

    public Long save(Profile profile) {
        Profile savedProfile = profileRepo.save(profile);
        return savedProfile.getId();
    }

    public void delete(Profile profile) {
        profileRepo.delete(profile);
    }
}
