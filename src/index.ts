import { Hospital } from "./hospital";

const hospital = new Hospital();

// Registrando um paciente
const patient1 = hospital.registerPatient({
    name: "João de tal",
    address: "Ruazinha qualquer, 1234",
    birthDate: new Date("1980-01-01"),
    healthPlan: "Plano de saude 1"
});

// Registrando um funcionario
const employee1 = hospital.registerEmployee({
    name: "Super doutor",
    address: "avenida dos médicos, 4321",
    birthDate: new Date("1975-05-05"),
    specialty: "Cardiologia",
    role: "Doctor"
});

// Registrando uma internação
const hospitalization1 = hospital.registerHospitalization({
    patientId: patient1.id,
    admissionDate: new Date("2024-06-01"),
    dischargeDate: null,
    bed: "101",
    procedures: ["Exame de sangue", "Eletrocardiograma"]
});

// Logando as informações
console.log(hospital.getPatient(patient1.id));
console.log(hospital.getEmployee(employee1.id));
console.log(hospital.getHospitalization(hospitalization1.id));

// Atualizando registros
hospital.updatePatient(patient1.id, { address: "789 Oak St" });
hospital.updateEmployee(employee1.id, { specialty: "Neurology" });
hospital.updateHospitalization(hospitalization1.id, { dischargeDate: new Date("2024-06-10") });

// Logando as informações atualizadas
console.log(hospital.getPatient(patient1.id));
console.log(hospital.getEmployee(employee1.id));
console.log(hospital.getHospitalization(hospitalization1.id));

// Deletando registros
hospital.deletePatient(patient1.id);
hospital.deleteEmployee(employee1.id);
hospital.deleteHospitalization(hospitalization1.id);

// Logando os relatórios / prontuarios
console.log(hospital.generatePatientReport());
console.log(hospital.generateEmployeeReport());
console.log(hospital.generateHospitalizationReport());