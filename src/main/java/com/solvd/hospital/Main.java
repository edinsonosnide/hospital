package com.solvd.hospital;

import com.solvd.hospital.concurrency.MyExtendsThreadClass;
import com.solvd.hospital.concurrency.MyImplementsRunnableClass;
import com.solvd.hospital.connection.Connection;
import com.solvd.hospital.connection.ConnectionPool;
import com.solvd.hospital.enums.Gender;
import com.solvd.hospital.enums.Month;
import com.solvd.hospital.enums.PainLevel;
import com.solvd.hospital.interfaces.AnnualSalaryCalculator;
import com.solvd.hospital.interfaces.PatientProcessor;
import com.solvd.hospital.interfaces.SymptomFilter;
import com.solvd.hospital.io.FileReader;
import com.solvd.hospital.io.FileWriter;
import com.solvd.hospital.model.device.Smartphone;
import com.solvd.hospital.model.facility.Hospital;
import com.solvd.hospital.model.facility.HospitalRoom;
import com.solvd.hospital.model.medical.Medicine;
import com.solvd.hospital.model.medical.Symptom;
import com.solvd.hospital.model.medical.Treatment;
import com.solvd.hospital.model.person.Administrator;
import com.solvd.hospital.model.person.Doctor;
import com.solvd.hospital.model.person.Patient;
import com.solvd.hospital.model.person.Person;
import com.solvd.hospital.model.storage.Backpack;
import com.solvd.hospital.model.storage.Box;
import com.solvd.hospital.model.storage.Briefcase;
import com.solvd.hospital.service.DiagnosisService;
import com.solvd.hospital.service.StandardDiagnosisService;
import com.solvd.hospital.service.StandardTreatmentService;
import com.solvd.hospital.service.TreatmentService;
import com.solvd.hospital.service.diagnosis.CovidDiagnosisRule;
import com.solvd.hospital.service.diagnosis.DiagnosisRule;
import com.solvd.hospital.service.diagnosis.PneumoniaDiagnosisRule;
import com.solvd.hospital.util.WordCounter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static {
        System.setProperty("log4j.configurationFile", "./src/main/resources/log4j2.xml");
    }

    public static final Logger LOGGER = LogManager.getLogger(Main.class);


    public static void main(String[] args) throws IOException, InterruptedException {

        LOGGER.info("This is info message");

        // Creation of objects
        Symptom chestPain = new Symptom("Chest Pain", PainLevel.LOW);
        Symptom fever = new Symptom("Fever", PainLevel.LOW);
        Symptom cough = new Symptom("Cough", PainLevel.LOW);
        Symptom difficultyBreathing = new Symptom("Difficulty Breathing", PainLevel.LOW);

        Patient john = new Patient("John", "Doe", new BigInteger("12345678"),38, Gender.MALE,"US - Minnesota", "jhon.doe@gmail.com", null, Month.JANUARY, null);

        List<Symptom> pneumoniaSymptoms = new ArrayList<>();
        pneumoniaSymptoms.add(chestPain);
        pneumoniaSymptoms.add(fever);
        pneumoniaSymptoms.add(cough);
        pneumoniaSymptoms.add(difficultyBreathing);

        john.setSymptoms(pneumoniaSymptoms);

        Hospital mayoClinic = new Hospital("Mayo Clinic - Rochester", "200 First Street SW Rochester, Minnesota 55905", new ArrayList<>(), new ArrayList<>());

        List<DiagnosisRule> diagnosisRules = List.of(
                new PneumoniaDiagnosisRule(),
                new CovidDiagnosisRule()
        );

        DiagnosisService diagnosisService = new StandardDiagnosisService( diagnosisRules );
        TreatmentService treatmentService = new StandardTreatmentService();
        Doctor<Medicine,Medicine,Medicine> liam = new Doctor<>("Liam", "Smith", new BigInteger("1234"), 40, Gender.MALE,"US - Minnesota", "liam.smith@gmail.com",null, new BigInteger("100000"), null, null, null, Month.JANUARY, diagnosisService, treatmentService);

        Administrator olivia = new Administrator("Olivia", "Rodrigo", new BigInteger("123"), 25,Gender.FEMALE,"US - Minnesota","olivia.rodrigo@gmail.com",null, new BigInteger("120000"), mayoClinic, Month.JANUARY);

        HospitalRoom secondRoomFirstFloor = new HospitalRoom(102, new ArrayList<>(), null);

        Smartphone samsung = new Smartphone("phone-1", "AB12", "Samsung", "S10","black","3100000000",true,50);
        Smartphone iphone = new Smartphone("phone-2","CD34","Apple", "11","black","3100000001",true,50);
        Smartphone honor = new Smartphone("phone-3","EF45","Honor", "6 lite","black","3100000002",true,50);

        // Everyone say Hi! (use of toString() five times)
        LOGGER.info("\n---toString() function checks and polymorphism check with Person subclasses administrator, patient and doctor:---");
        Person[] everyone = {john, liam, olivia};
        Arrays.stream(everyone).map(Person::toString).forEach(LOGGER::info);

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
        olivia.getHospital().getTreatments().forEach(treatment -> olivia.getPhone().sendMessage(olivia, treatment.getPatient(), "\"Treatment description: " + treatment.getDescription() + "\""));


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
        medicineForFlueList.forEach(LOGGER::info);

        LOGGER.info("2. Set:");
        Set<Medicine> medicineForFlueSet = new HashSet<>(medicineForFlueList);
        medicineForFlueSet.forEach(LOGGER::info);

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
        liam.getBox().getThings().forEach(medicine -> LOGGER.info(medicine.toString()));


        LOGGER.info("---2. Generic class backpack---");
        liam.setBackpack(backpackOfMedicineForFlue);
        liam.getBackpack().getThings().forEach(medicine -> LOGGER.info(medicine.toString()));

        LOGGER.info("---3. Generic class briefcase---");
        liam.setBriefcase(briefcaseMOfMedicinesForFlue);
        liam.getBriefcase().getThings().forEach(medicine -> LOGGER.info(medicine.toString()));

        LOGGER.info("--- Start of commons.io read and write files---");
        String fileNameInput =  System.getProperty("user.dir")+"\\src\\main\\resources\\SOLID principles summary.txt";
        String fileNameOutput = System.getProperty("user.dir")+"\\src\\main\\resources\\result of counting.txt";
        FileReader fileReader = new FileReader();
        String resultOfReading = fileReader.readFile(fileNameInput);
        LOGGER.info(resultOfReading);
        WordCounter wordCounter = new WordCounter();
        String wordCount = wordCounter.getWordCounter(resultOfReading, new String[]{"SRP","OCP","LSP","OCP","LSP","ISP"});
        LOGGER.info("\n{}", wordCount);
        FileWriter.writeFile(fileNameOutput,wordCount);

        LOGGER.info("--- Start of 3 functional interfaces ---");
        AnnualSalaryCalculator annualSalaryCalculator = (employee) -> employee.getMonthlySalary().multiply(new BigInteger(String.valueOf(12)));
        PatientProcessor patientProcessor = (patient) -> LOGGER.info(patient.toString());
        SymptomFilter feverFilter = (symptom) -> symptom.name().equals("Fever");

        BigInteger doctorAnnualSalary = annualSalaryCalculator.calculateAnnualSalary(liam);
        LOGGER.info("Doctor {} annual salary: {}", liam.getFirstName(), doctorAnnualSalary.toString());
        patientProcessor.displayPatient(john);
        Symptom mediumFever = new Symptom("Fever", PainLevel.LOW);
        List<Symptom> unknownSymptoms = new ArrayList<>();
        unknownSymptoms.add(chestPain);
        unknownSymptoms.add(fever);
        unknownSymptoms.add(mediumFever);
        unknownSymptoms.add(cough);
        unknownSymptoms.add(difficultyBreathing);
        unknownSymptoms.stream().filter(feverFilter::matches).forEach(symptom -> LOGGER.info("Symptom with name fever found: {}",symptom.name()));

        LOGGER.info("---Start of the use of threads---");
        //2. Create 2 threads — one using extends Thread, one using implements Runnable
        MyImplementsRunnableClass myImplementsRunnableClass = new MyImplementsRunnableClass("myImplementsRunnableClass");
        MyExtendsThreadClass myExtendsThreadClass = new MyExtendsThreadClass(myImplementsRunnableClass, "myExtendsThreadClass");

        myImplementsRunnableClass.run();
        myExtendsThreadClass.start();
        myExtendsThreadClass.runTask();
        //3. Create a Connection Pool.
        ConnectionPool pool = ConnectionPool.getInstance();

        // 4. Initialize the pool with size 5. Submit 7 borrow-then-release tasks to a fixed thread pool of size 7.
        ExecutorService workers = Executors.newFixedThreadPool(7);

        Runnable task = () -> {
            try {
                String name = Thread.currentThread().getName();
                LOGGER.info(name + " → waiting for connection...");
                Connection c = pool.acquire();
                LOGGER.info(name + " → got " + c);
                Thread.sleep(2_000);
                pool.release(c);
                LOGGER.info(name + " → released " + c);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        for (int i = 0; i < 7; i++) workers.submit(task);

        workers.shutdown();
        LOGGER.info(workers.awaitTermination(10, TimeUnit.SECONDS));
        //pool.shutdown();

        // 5. Implement part 4 again using Future / CompletionStage
        ExecutorService io  = Executors.newFixedThreadPool(7);

        List<CompletableFuture<Void>> stages = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            int taskId = i;
            CompletableFuture<Void> stage = CompletableFuture
                    .supplyAsync(() -> {
                        try {
                            return acquireOrThrow(pool, taskId);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }, io)
                    .thenAcceptAsync(conn -> {
                        try {
                            useAndRelease(pool, conn, taskId);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }, io)
                    .exceptionally(err -> {
                        System.err.println("task-" + taskId + " failed: " + err);
                        return null;
                    });
            stages.add(stage);
        }

        CompletableFuture
                .allOf(stages.toArray(new CompletableFuture[0]))
                .join();   // main thread waits here

        io.shutdown();
        pool.shutdown();
    }

    private static Object acquireOrThrow(ConnectionPool pool, int taskId) throws InterruptedException {
        Connection c = pool.acquire();
        String name = Thread.currentThread().getName();
        LOGGER.info(name + " → got " + c);

        return c;
    }

    private static <U> void useAndRelease(ConnectionPool pool, U conn, int taskId) throws InterruptedException {
        Thread.sleep(2_000);
        String name = Thread.currentThread().getName();
        pool.release((Connection) conn);
        LOGGER.info(name + " → released " + conn);
    }
}