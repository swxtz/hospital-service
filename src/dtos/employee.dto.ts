import type { Person } from "./person.dto";

export interface Employee extends Person {
    specialty: string;
    role: string;
}