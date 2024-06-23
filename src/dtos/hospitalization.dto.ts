export interface Hospitalization {
    id: number;
    patientId: number;
    admissionDate: Date;
    dischargeDate: Date | null;
    bed: string;
    procedures: string[];
}