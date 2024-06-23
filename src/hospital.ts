import type { Employee } from "./dtos/employee.dto";
import type { Hospitalization } from "./dtos/hospitalization.dto";
import type { Patient } from "./dtos/pacient.dto";

class Hospital {
    private patients: Patient[] = [];
    private employees: Employee[] = [];
    private hospitalizations: Hospitalization[] = [];

    private generateId(collection: any[]): number {
        return collection.length ? collection[collection.length - 1].id + 1 : 1;
    }

    // Metodo para paciente
    registerPatient(patient: Omit<Patient, 'id'>): Patient {
        const newPatient = { ...patient, id: this.generateId(this.patients) };
        this.patients.push(newPatient);
        return newPatient;
    }

    getPatient(id: number): Patient | undefined {
        return this.patients.find(p => p.id === id);
    }

    updatePatient(id: number, data: Partial<Patient>): Patient | undefined {
        const patient = this.getPatient(id);
        if (patient) {
            Object.assign(patient, data);
        }
        return patient;
    }

    deletePatient(id: number): boolean {
        const index = this.patients.findIndex(p => p.id === id);
        if (index !== -1) {
            this.patients.splice(index, 1);
            return true;
        }
        return false;
    }

    generatePatientReport(): Patient[] {
        return this.patients;
    }

    // Metodo para empregados
    registerEmployee(employee: Omit<Employee, 'id'>): Employee {
        const newEmployee = { ...employee, id: this.generateId(this.employees) };
        this.employees.push(newEmployee);
        return newEmployee;
    }

    getEmployee(id: number): Employee | undefined {
        return this.employees.find(e => e.id === id);
    }

    updateEmployee(id: number, data: Partial<Employee>): Employee | undefined {
        const employee = this.getEmployee(id);
        if (employee) {
            Object.assign(employee, data);
        }
        return employee;
    }

    deleteEmployee(id: number): boolean {
        const index = this.employees.findIndex(e => e.id === id);
        if (index !== -1) {
            this.employees.splice(index, 1);
            return true;
        }
        return false;
    }

    generateEmployeeReport(): Employee[] {
        return this.employees;
    }

    // Metodos para hospitalização
    registerHospitalization(hospitalization: Omit<Hospitalization, 'id'>): Hospitalization {
        const newHospitalization = { ...hospitalization, id: this.generateId(this.hospitalizations) };
        this.hospitalizations.push(newHospitalization);
        return newHospitalization;
    }

    getHospitalization(id: number): Hospitalization | undefined {
        return this.hospitalizations.find(h => h.id === id);
    }

    updateHospitalization(id: number, data: Partial<Hospitalization>): Hospitalization | undefined {
        const hospitalization = this.getHospitalization(id);
        if (hospitalization) {
            Object.assign(hospitalization, data);
        }
        return hospitalization;
    }

    deleteHospitalization(id: number): boolean {
        const index = this.hospitalizations.findIndex(h => h.id === id);
        if (index !== -1) {
            this.hospitalizations.splice(index, 1);
            return true;
        }
        return false;
    }

    generateHospitalizationReport(): Hospitalization[] {
        return this.hospitalizations;
    }
}