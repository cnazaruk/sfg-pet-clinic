package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Fiona");
        owner1.setLastName("Glenanne");
        owner1.setAddress("123 Street");
        owner1.setCity("Belper");
        owner1.setTelephone("012345");

        Pet firstPet = new Pet();
        firstPet.setPetType(savedDogPetType);
        firstPet.setOwner(owner1);
        firstPet.setBirthDate(LocalDate.now());
        firstPet.setName("Goose");
        owner1.getPets().add(firstPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Michael");
        owner2.setLastName("Weston");
        owner2.setAddress("456 Street");
        owner2.setCity("Derby");
        owner2.setTelephone("567890");

        Pet secondPet = new Pet();
        secondPet.setPetType(savedCatPetType);
        secondPet.setOwner(owner2);
        secondPet.setBirthDate(LocalDate.now());
        secondPet.setName("Gato");
        owner2.getPets().add(secondPet);

        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Alan");
        vet2.setLastName("Rickman");

        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
