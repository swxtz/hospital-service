package com.swxtz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Interfaces
interface Person {
    int getId();
    String getName();
    String getAddress();
    Date getBirthDate();
}

interface Employee extends Person {
    String getSpecialty();
    String getRole();
}

interface Hospitalization {
    int getId();
    int getPatientId();
    Date getAdmissionDate();
    Date getDischargeDate();
    String getBed();
    List<String> getProcedures();
}

interface Patient extends Person {
    String getHealthPlan();
}

// Implementações das Interfaces
class PersonImpl implements Person {
    private int id;
    private String name;
    private String address;
    private Date birthDate;

    public PersonImpl(int id, String name, String address, Date birthDate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Date getBirthDate() {
        return birthDate;
    }
}

class EmployeeImpl extends PersonImpl implements Employee {
    private String specialty;
    private String role;

    public EmployeeImpl(int id, String name, String address, Date birthDate, String specialty, String role) {
        super(id, name, address, birthDate);
        this.specialty = specialty;
        this.role = role;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getRole() {
        return role;
    }
}

class PatientImpl extends PersonImpl implements Patient {
    private String healthPlan;

    public PatientImpl(int id, String name, String address, Date birthDate, String healthPlan) {
        super(id, name, address, birthDate);
        this.healthPlan = healthPlan;
    }

    public String getHealthPlan() {
        return healthPlan;
    }
}

class HospitalizationImpl implements Hospitalization {
    private int id;
    private int patientId;
    private Date admissionDate;
    private Date dischargeDate;
    private String bed;
    private List<String> procedures;

    public HospitalizationImpl(int id, int patientId, Date admissionDate, Date dischargeDate, String bed, List<String> procedures) {
        this.id = id;
        this.patientId = patientId;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.bed = bed;
        this.procedures = procedures;
    }

    public int getId() {
        return id;
    }

    public int getPatientId() {
        return patientId;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public String getBed() {
        return bed;
    }

    public List<String> getProcedures() {
        return procedures;
    }
}

// Classe Hospital
class Hospital {
    private List<Patient> patients = new ArrayList<>();
    private List<Employee> employees = new ArrayList<>();
    private List<Hospitalization> hospitalizations = new ArrayList<>();

    private int generateId(List<?> collection) {
        return collection.size() + 1;
    }

    // Métodos para pacientes
    public Patient registerPatient(Patient patient) {
        ((PatientImpl) patient).setId(generateId(patients));
        patients.add(patient);
        return patient;
    }

    public Patient getPatient(int id) {
        return patients.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public Patient updatePatient(int id, Patient data) {
        Patient patient = getPatient(id);
        if (patient != null) {
            patients.set(patients.indexOf(patient), data);
        }
        return patient;
    }

    public boolean deletePatient(int id) {
        Patient patient = getPatient(id);
        if (patient != null) {
            patients.remove(patient);
            return true;
        }
        return false;
    }

    public List<Patient> generatePatientReport() {
        return patients;
    }

    // Métodos para empregados
    public Employee registerEmployee(Employee employee) {
        ((EmployeeImpl) employee).setId(generateId(employees));
        employees.add(employee);
        return employee;
    }

    public Employee getEmployee(int id) {
        return employees.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    public Employee updateEmployee(int id, Employee data) {
        Employee employee = getEmployee(id);
        if (employee != null) {
            employees.set(employees.indexOf(employee), data);
        }
        return employee;
    }

    public boolean deleteEmployee(int id) {
        Employee employee = getEmployee(id);
        if (employee != null) {
            employees.remove(employee);
            return true;
        }
        return false;
    }

    public List<Employee> generateEmployeeReport() {
        return employees;
    }

    // Métodos para hospitalizações
    public Hospitalization registerHospitalization(Hospitalization hospitalization) {
        ((HospitalizationImpl) hospitalization).setId(generateId(hospitalizations));
        hospitalizations.add(hospitalization);
        return hospitalization;
    }

    public Hospitalization getHospitalization(int id) {
        return hospitalizations.stream().filter(h -> h.getId() == id).findFirst().orElse(null);
    }

    public Hospitalization updateHospitalization(int id, Hospitalization data) {
        Hospitalization hospitalization = getHospitalization(id);
        if (hospitalization != null) {
            hospitalizations.set(hospitalizations.indexOf(hospitalization), data);
        }
        return hospitalization;
    }

    public boolean deleteHospitalization(int id) {
        Hospitalization hospitalization = getHospitalization(id);
        if (hospitalization != null) {
            hospitalizations.remove(hospitalization);
            return true;
        }
        return false;
    }

    public List<Hospitalization> generateHospitalizationReport() {
        return hospitalizations;
    }

    public static void main(String[] args) {
        Hospital hospital = new Hospital();

        // Registrando um paciente
        Patient patient1 = new PatientImpl(0, "João de Tal", "Rua qualquer, 1234", new Date(1980, 1, 1), "Plano de Saúde 1");
        hospital.registerPatient(patient1);

        // Registrando um funcionário
        Employee employee1 = new EmployeeImpl(0, "Super Doutor", "Avenida dos Médicos, 4321", new Date(1975, 5, 5), "Cardiologia", "Doutor");
        hospital.registerEmployee(employee1);

        // Registrando uma internação
        List<String> procedures = new ArrayList<>();
        procedures.add("Exame de sangue");
        procedures.add("Eletrocardiograma");

        Hospitalization hospitalization1 = new HospitalizationImpl(0, patient1.getId(), new Date(2024, 6, 1), null, "101", procedures);
        hospital.registerHospitalization(hospitalization1);

        // Logando as informações
        System.out.println(hospital.getPatient(patient1.getId()));
        System.out.println(hospital.getEmployee(employee1.getId()));
        System.out.println(hospital.getHospitalization(hospitalization1.getId()));

        // Atualizando registros
        Patient updatedPatient = new PatientImpl(patient1.getId(), patient1.getName(), "789 Oak St", patient1.getBirthDate(), patient1.getHealthPlan());
        hospital.updatePatient(patient1.getId(), updatedPatient);

        Employee updatedEmployee = new EmployeeImpl(employee1.getId(), employee1.getName(), employee1.getAddress(), employee1.getBirthDate(), "Neurologia", employee1.getRole());
        hospital.updateEmployee(employee1.getId(), updatedEmployee);

        Hospitalization updatedHospitalization = new HospitalizationImpl(hospitalization1.getId(), hospitalization1.getPatientId(), hospitalization1.getAdmissionDate(), new Date(2024, 6, 10), hospitalization1.getBed(), hospitalization1.getProcedures());
        hospital.updateHospitalization(hospitalization1.getId(), updatedHospitalization);

        // Logando as informações atualizadas
        System.out.println(hospital.getPatient(patient1.getId()));
        System.out.println(hospital.getEmployee(employee1.getId()));
        System.out.println(hospital.getHospitalization(hospitalization1.getId()));

        // Deletando registros
        hospital.deletePatient(patient1.getId());
        hospital.deleteEmployee(employee1.getId());
        hospital.deleteHospitalization(hospitalization1.getId());

        // Logando os relatórios
        System.out.println(hospital.generatePatientReport());
        System.out.println(hospital.generateEmployeeReport());
        System.out.println(hospital.generateHospitalizationReport());
    }
}
