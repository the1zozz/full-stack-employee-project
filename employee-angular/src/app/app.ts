import { Component, OnInit, signal } from '@angular/core';
import { Employee } from './employee/employee';
import { EmployeeService } from './employee/employee.service';
import { HttpErrorResponse } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  styleUrl: './app.css',
  imports:[CommonModule,FormsModule]
})
export class App implements OnInit {


  public employees: Employee[] = [];

  public editEmployee?: Employee;
  public deleteEmployee?: Employee;

  constructor(private employeeService: EmployeeService) {}

  ngOnInit(): void {
    this.getEmployees();
  }

  public getEmployees(): void {
    this.employeeService.getEmployees().subscribe(
      (Response: Employee[]) => {
        this.employees = Response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  onAddEmployee(addForm: NgForm): void {
    document.getElementById('add-employee-form')?.click();
    this.employeeService.addEmployee(addForm.value).subscribe(
      (Response:Employee) => {
        console.log(Response);
        this.getEmployees();
        addForm.reset();
      },
      (error:HttpErrorResponse) => {
        alert(error.message);
      }
    )
}

onUpdateEmployee(employee:Employee):void {
  this.employeeService.updateEmployee(employee).subscribe(
    (Response:Employee) => {
      console.log(Response);
      this.getEmployees();
    },
    (error:HttpErrorResponse) => {
      alert(error.message);
    }
  )
}
  public onDeleteEmployee(employeeId: number): void {
    this.employeeService.deleteEmployee(employeeId).subscribe(
      (Response:void) => {
        console.log(Response);
        this.getEmployees();
      },
      (error:HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  public searchEmployees(key: string): void {
    console.log(key);
    const results: Employee[] = [];
    for(const employee of this.employees){
      if(employee.name.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || employee.email.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || employee.jobTitle.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || employee.phone.toLowerCase().indexOf(key.toLowerCase()) !== -1){
        results.push(employee);
      }
    }
    this.employees = results;
    if(results.length === 0 || !key){
      this.getEmployees();
    }
  }

  public onOpenModal(employee: Employee | null,mode:string):void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addEmployeeModal');
    } 
    if (mode === 'edit') {
      this.editEmployee = employee!;
      button.setAttribute('data-target', '#updateEmployeeModal');
    }
    if (mode === 'delete') {
      this.deleteEmployee = employee!;
      button.setAttribute('data-target', '#deleteEmployeeModal');
    }
    container?.appendChild(button);
    button.click();
  }


}