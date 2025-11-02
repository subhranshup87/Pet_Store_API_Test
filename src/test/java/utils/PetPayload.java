package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.pet.Pet;

public class PetPayload {

    public static String generate(int id, String name, String category, String status) {
        try {
            Pet pet = new Pet(id, name, category, status);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(pet);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate pet payload", e);
        }
    }
}