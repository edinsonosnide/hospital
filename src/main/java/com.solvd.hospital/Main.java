package com.solvd.hospital;

import com.solvd.hospital.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static {
        System.setProperty("log4j.configurationFile", "./src/main/resources/log4j2.xml");
    }

    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        LOGGER.info("This is info message");

        // Creation of objects
        Symptom chestPain = new Symptom("Chest Pain", PainLevel.LOW);
        Symptom fever = new Symptom("Fever", PainLevel.LOW);
        Symptom cough = new Symptom("Cough", PainLevel.LOW);
        Symptom difficultyBreathing = new Symptom("Difficulty Breathing", PainLevel.LOW);

        Patient john = new Patient("John", "Doe", new BigInteger("12345678"),38,Gender.MALE,"US - Minnesota", "jhon.doe@gmail.com", null, Month.JANUARY, null);

        List<Symptom> pneumoniaSymptoms = new ArrayList<>();
        pneumoniaSymptoms.add(chestPain);
        pneumoniaSymptoms.add(fever);
        pneumoniaSymptoms.add(cough);
        pneumoniaSymptoms.add(difficultyBreathing);

        john.setSymptoms(pneumoniaSymptoms);

        Hospital mayoClinic = new Hospital("Mayo Clinic - Rochester", "200 First Street SW Rochester, Minnesota 55905", new ArrayList<>(), new ArrayList<>());

        Doctor liam = new Doctor("Liam", "Smith", new BigInteger("1234"), 40, Gender.MALE,"US - Minnesota", "liam.smith@gmail.com",null, new BigInteger("100000"), null, null, null, Month.JANUARY);

        Administrator olivia = new Administrator("Olivia", "Rodrigo", new BigInteger("123"), 25,Gender.FEMALE,"US - Minnesota","olivia.rodrigo@gmail.com",null, new BigInteger("120000"), mayoClinic, Month.JANUARY);

        HospitalRoom secondRoomFirstFloor = new HospitalRoom(102, new ArrayList<>(), null);

        Smartphone samsung = new Smartphone("phone-1", "AB12", "Samsung", "S10","black","3100000000",true,50);
        Smartphone iphone = new Smartphone("phone-2","CD34","Apple", "11","black","3100000001",true,50);
        Smartphone honor = new Smartphone("phone-3","EF45","Honor", "6 lite","black","3100000002",true,50);

        // Everyone say Hi! (use of toString() five times)
        LOGGER.info("\n---toString() function checks and polymorphism check with Person subclasses administrator, patient and doctor:---");
        Person[] everyone = {john, liam, olivia};
        for (Person person: everyone){
            LOGGER.info(person.toString());
        }

        LOGGER.info(mayoClinic.toString());
        LOGGER.info(samsung.toString());
        LOGGER.info(iphone.toString());
        LOGGER.info(honor.toString());

        //comparisons (equals())
        LOGGER.info("\n---equals() function checks:---");
        Patient johnClone = new Patient("John", "Doe", new BigInteger("12345678"),38,Gender.MALE,"US - Minnesota", "jhon.doe@gmail.com", null, Month.JANUARY ,null);
        LOGGER.info("Are these Johns the same person? {}", johnClone.equals(john));

        Hospital mayoClinicClone = new Hospital("Mayo Clinic - Rochester", "200 First Street SW Rochester, Minnesota 55905", new ArrayList<>(), new ArrayList<>());
        LOGGER.info("Are these hospitals the same? {}", mayoClinicClone.equals(mayoClinic));

        Smartphone samsungClone = new Smartphone("phone-1", "AB12", "Samsung", "S10","black","3100000000",true, 50);
        LOGGER.info("Are these smartphones the same? {}\n", samsungClone.equals(samsung));

        LOGGER.info("---Start of story---");

        // Creation of relationships between objects
        john.setPhone(samsung);
        liam.setPhone(iphone);
        olivia.setPhone(honor);

        // Logic of the flow of interactions between objects
        olivia.getHospital().getHospitalRooms().add(secondRoomFirstFloor);
        olivia.getHospital().getHospitalRooms().getFirst().addPatient(john);
        olivia.getHospital().getHospitalRooms().getFirst().setDoctor(liam);

        // Administrator makes hospital treat all patients
        olivia.getHospital().treatAllPatients();

        // Administrator sends message about treatment to all patients
        for (Treatment treatment : olivia.getHospital().getTreatments()){
            olivia.getPhone().sendMessage(olivia, treatment.getPatient(),"\"Treatment description: " + treatment.getDescription() + "\"");
        }

        LOGGER.info("\n---Start of use of interfaces---");
        // Use of interfaces
        LOGGER.info("---First use---");
        System.out.print("Olivia´s smartphone: ");
        olivia.getPhone().getPercentageOfBattery();
        olivia.getPhone().rechargeBatteryCompletely();
        System.out.print("Olivia´s smartphone: ");
        olivia.getPhone().getPercentageOfBattery();
        LOGGER.info("---Second use---");
        for (Treatment treatment : olivia.getHospital().getTreatments()){
            treatment.getPatient().followTreatment(treatment);
            if (treatment.getPatient().getSymptoms().isEmpty()){
                LOGGER.info("The patient {}{} has no symptoms", treatment.getPatient().getFirstName(), treatment.getPatient().getLastName());
            }
            else {
                LOGGER.info("The patient {}{} has symptoms still", treatment.getPatient().getFirstName(), treatment.getPatient().getLastName());
            }
        }
        LOGGER.info("---Third use---");
        LOGGER.info("TreatsPatients interface (refactored, not new) used internally in this call (line 74): olivia.getHospital().treatAllPatients();");
        LOGGER.info("---Fourth use---");
        LOGGER.info("Patient {}{} is so happy he was cured, that he plays a song", john.getFirstName(), john.getLastName());
        john.getPhone().playSong("I wonder - Kanye West");
        LOGGER.info("---Fifth use---");
        LOGGER.info("Patient {}{} is so happy he was cured, that he takes a selfie", john.getFirstName(), john.getLastName());
        john.getPhone().takeSelfie();

        LOGGER.info("---The doctor organize the medicines he has in his consulting room---");

        Medicine paracetamol = new Medicine("Paracetamol", "It lowers the production of substances in the body that cause pain and fever.");
        Medicine loratadine = new Medicine("Loratadine", "Blocks histamine, a substance responsible for allergy-like symptoms.");

        LOGGER.info("---Start of use of 5 different collections---");

        LOGGER.info("1. List:");
        List<Medicine> medicineForFlueList = Arrays.asList(paracetamol,loratadine);
        for (Medicine medicine : medicineForFlueList) {
            LOGGER.info(medicine);
        }

        LOGGER.info("2. Set:");
        Set<Medicine> medicineForFlueSet = new HashSet<>(medicineForFlueList);
        for (Medicine medicineForFlue : medicineForFlueSet) {
            LOGGER.info(medicineForFlue);
        }

        LOGGER.info("3. Queue:");
        Queue<Medicine> medicineForFlueQueue = new LinkedList<>(medicineForFlueSet);
        while (!medicineForFlueQueue.isEmpty()) {
            LOGGER.info(medicineForFlueQueue.poll());
            if (medicineForFlueQueue.isEmpty()) {
                LOGGER.info("queue is empty");
            }
        }

        LOGGER.info("4. Deque:");
        Deque<Medicine>  medicineForFlueDeque = new LinkedList<>(medicineForFlueSet);
        while (!medicineForFlueDeque.isEmpty()) {
            LOGGER.info(medicineForFlueDeque.poll());
            if (medicineForFlueDeque.isEmpty()) {
                LOGGER.info("deque is empty");
            }
        }

        LOGGER.info("5. Stack:");
        Stack<Medicine> medicineForFlueStack = new Stack<>();
        medicineForFlueStack.push(paracetamol);
        medicineForFlueStack.push(loratadine);
        while(!medicineForFlueStack.isEmpty()) {
            LOGGER.info(medicineForFlueStack.pop());
            if (medicineForFlueStack.isEmpty()) {
                LOGGER.info("stack is empty");
            }
        }

        LOGGER.info("---Start of use of 3 generic classes---");

        LOGGER.info("---The doctor packs the medicines for flue he has in his consulting room in a box, backpack and briefcase: ---");

        Box<Medicine> boxOfMedicineForFlue = new Box<>(medicineForFlueList);
        Backpack<Medicine> backpackOfMedicineForFlue = new Backpack<>(medicineForFlueList);
        Briefcase<Medicine> briefcaseMOfMedicinesForFlue = new Briefcase<>(medicineForFlueList);

        LOGGER.info("---1. Generic class box---");
        liam.setBox(boxOfMedicineForFlue);
        for (Object medicine : liam.getBox().getThings()){
            LOGGER.info(medicine.toString());
        }

        LOGGER.info("---2. Generic class backpack---");
        liam.setBackpack(backpackOfMedicineForFlue);
        for (Object medicine : liam.getBox().getThings()){
            LOGGER.info(medicine.toString());
        }

        LOGGER.info("---3. Generic class briefcase---");
        liam.setBriefcase(briefcaseMOfMedicinesForFlue);
        for (Object medicine : liam.getBox().getThings()){
            LOGGER.info(medicine.toString());
        }

        LOGGER.info("--- Start of commons.io read and write files---");
        String fileNameInput =  System.getProperty("user.dir")+"\\src\\main\\resources\\SOLID principles summary.txt";
        String fileNameOutput = System.getProperty("user.dir")+"\\src\\main\\resources\\result of counting.txt";
        FileReader fileReader = new FileReader();
        String resultOfReading = fileReader.readFile(fileNameInput);
        LOGGER.info(resultOfReading);
        WordCounter wordCounter = new WordCounter();
        String wordCount = wordCounter.WordCounter(resultOfReading, new String[]{"SRP","OCP","LSP","OCP","LSP","ISP"});
        LOGGER.info("\n{}", wordCount);
        FileWriter.writeFile(fileNameOutput,wordCount);
    }
}