package com.control_inventario.Controllers;

import com.control_inventario.Models.Profile;
import com.control_inventario.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/profile")
    public ResponseEntity<String> createProfile(@RequestBody Profile profile) {
        Long savedProfile = profileService.save(profile);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        URI location = URI.create("/api/profile/" + savedProfile);
        return ResponseEntity.created(location).headers(headers).body("Profile created successfully");
    }

    @GetMapping("/profile")
    public ResponseEntity<List<Profile>> listProfiles() {
        List<Profile> profiles = profileService.list();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        return ResponseEntity.ok().headers(headers).body(profiles);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable Long id) {
        Optional<Profile> profileOptional = profileService.getProfileById(id);

        if (profileOptional.isPresent()) {
            Profile profile = profileOptional.get();
            return ResponseEntity.ok().body(profile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/profile/{id}")
    public ResponseEntity<String> updateProfile(@PathVariable Long id, @RequestBody Profile profile) {
        Optional<Profile> existingProfileOptional = profileService.getProfileById(id);

        if (existingProfileOptional.isPresent()) {
            Profile existingProfile = existingProfileOptional.get();
            existingProfile.setName(profile.getName());
            existingProfile.setRole(profile.getRole());

            profileService.save(existingProfile);

            return ResponseEntity.ok().body("Profile updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/profile/{id}")
    public ResponseEntity<String> deleteProfile(@PathVariable Long id) {
        Optional<Profile> profileOptional = profileService.getProfileById(id);

        if (profileOptional.isPresent()) {
            profileService.delete(profileOptional.get());
            return ResponseEntity.ok().body("Profile deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
