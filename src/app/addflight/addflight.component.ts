import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from '../admin.service';

@Component({
  selector: 'app-addflight',
  templateUrl: './addflight.component.html',
  styleUrls: ['./addflight.component.css']
})
export class AddflightComponent implements OnInit {

  constructor(private adminService:AdminService,private router: Router) { }
  flight:any={};
  flights=[{flightName:"",fromPlace:"",toPlace:"",cost:"",isActive:""}];
  ngOnInit(): void {
    this.getAllFlights();
    let details=window.sessionStorage.getItem("edit");
    if(details!=null){
      this.flight=JSON.parse(details);
    }
  }
  addflight(flight:any){
  this.adminService.addflight(flight).subscribe({
    next:(res)=>this.handleResponse(res),
    error:(err)=>this.handleError(err)
  })
}
  handleResponse(res: any) {
   alert("you have sucessfully added flight");
  }

  handleError(err:any) {
    alert("Please try again.");
  }

  getAllFlights(){
    this.adminService.getAllFlights().subscribe({
      next:(res)=>{
      this.flights=res;
      }
    })
  }
  deleteFlight(flight:any){
    console.log(flight);
    this.adminService.deleteFlight(flight.flightID).subscribe({
      next:(res)=>{
        this.getAllFlights();
        alert("Flight deleted successfully");
      }
     
    })
  }
}

