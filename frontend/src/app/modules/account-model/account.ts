import { Person } from "../person-model/person";
import { Address } from "./address";


export interface Account {
    person: Person;
    address: Address;
    deposit: number;
    cardNumber: string;
    phoneNumber: string;
    password:string;
}
