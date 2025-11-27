import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Employee } from "./employee";
import { Injectable } from "@angular/core";
import { environment } from "../../environments/environment";
@Injectable({
    providedIn: 'root'
})
export class EmployeeService {
    private apiServerUrl = environment.apiBaseUrl ; // Add your API server URL here  

    constructor(private http : HttpClient) {}

    public getEmployees(): Observable<Employee[]> {
        return this.http.get<Employee[]>(`${this.apiServerUrl}/find/all`);
}
    public addEmployee(employee: Employee): Observable<Employee> {
        return this.http.post<Employee>(`${this.apiServerUrl}/add`, employee);
}
    public updateEmployee(employee:Employee): Observable<Employee> {
        return this.http.put<Employee>(`${this.apiServerUrl}/update/${employee.id}`, employee);
}
    public deleteEmployee(employeeId: number): Observable<void> {
        return this.http.delete<void>(`${this.apiServerUrl}/delete/${employeeId}`);
}
}