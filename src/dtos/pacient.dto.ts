import type { Person } from "./person.dto";

export interface Patient extends Person {
    healthPlan: string;
}