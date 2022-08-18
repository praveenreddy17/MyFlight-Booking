import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Flight } from './flight';
const httpOptions={
  headers: new HttpHeaders({'Content-Type':'application/json', 'Access-Control-Allow-Origin': '*',
  'Access-Control-Allow-Headers': 'Content-Type',
  'Access-Control-Allow-Methods': 'GET,POST,OPTIONS,DELETE,PUT'})
}
const httpOptionsUTF8={
  headers: new HttpHeaders({'Content-Type':'application/json;charset=UTF-8', 'Access-Control-Allow-Origin': '*',
  'Access-Control-Allow-Headers': 'Content-Type',
  'Access-Control-Allow-Methods': 'GET,POST,OPTIONS,DELETE,PUT'})
}
const httpEncodeOptions={
  headers: new HttpHeaders({'Content-Type':'application/x-www-form-urlencoded', 'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Headers': 'Content-Type',
      'Access-Control-Allow-Methods': 'GET,POST,OPTIONS,DELETE,PUT'})
}

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  [x: string]: any;
  constructor(private http:HttpClient) { }
  private isLoggedIn=new BehaviorSubject<boolean>(false);
  isLoggedInStr=this.isLoggedIn.asObservable();
  private isAdmin=new BehaviorSubject<boolean>(false);
  isAdminStr=this.isAdmin.asObservable();
  setIsAdmin(isAdmin:boolean){
    this.isAdmin.next(isAdmin);
  }
  getIsAdmin(){
    this.isAdmin.asObservable();
  }

  setIsLoggedIn(isLoggedIn:boolean){
    this.isLoggedIn.next(isLoggedIn);
  }
  getIsLoggedIn(){
    this.isLoggedIn.asObservable();
  }

  public login(user:any):Observable<any>{
    return this.http.post("http://localhost:8081/user/login",user,httpOptions).pipe(
      tap(res=>res)
    );
  }
  public searchFlights(search:any):Observable<any>{
    return this.http.post("http://localhost:8081/user/searchFlights",search,httpOptions).pipe(
      tap(res=>res)
    );
  }
  public createUser(Flight:any):Observable<any>{
    return this.http.post("http://localhost:8081/user/createUser",Flight,httpOptions).pipe(
      tap(res=>res)
    );
  }
  public updateUser(Flight:any):Observable<any>{
    return this.http.post("http://localhost:8081/user/updateUser",Flight,httpOptions).pipe(
      tap(res=>res)
    );
  }

  public getUser(id:number):Observable<any>{
    let params = new HttpParams();
    params.append("id",id);
    return this.http.post("http://localhost:8081/user/getUser",params,httpEncodeOptions).pipe(
      tap(res=>res)
    );
  }
  public deleteUser(id:number):Observable<any>{
    let params = new HttpParams();
    params.append("id",id);
    return this.http.post("http://localhost:8081/user/deleteUser",params,httpEncodeOptions).pipe(
      tap(res=>res)
    );
  }

  public getUserFlights(id:any):Observable<any>{
    let params = new HttpParams();
    params.append("id",id);
    return this.http.post("http://localhost:8081/user/getUserFlights/"+id,params,httpEncodeOptions).pipe(
      tap(res=>res)
    );
  }
  public bookFlight(booking:any):Observable<any>{
    return this.http.post("http://localhost:8081/user/bookFlight",booking,httpOptions).pipe(
      tap(res=>res)
    );
  }
  public addflight(flightObj:any):Observable<any>{
    return this.http.post("http://localhost:8083/flight/addflight",flightObj,httpOptions).pipe(
      tap(res=>res)
    );
  }

  public getAllFlights():Observable<any>{
    return this.http.post("http://localhost:8083/flight/getAllFlights",httpOptions).pipe(
      tap(res=>res)
    );
  }
  public deleteUserFlight(id:number):Observable<any>{
    let params = new HttpParams();
    params.append("id",id);
    return this.http.post("http://localhost:8081/user/deleteUserFlight/"+id,params,httpEncodeOptions).pipe(
      tap(res=>res)
    );
  }

  public deleteFlight(id:number):Observable<any>{
    console.log("id"+id);
    let params = new HttpParams();
    params.append("id",id); 
    return this.http.post("http://localhost:8083/flight/deleteFlight/"+id,params,httpEncodeOptions).pipe(
      tap(res=>res)
    );
  }
  public updateFlight(flightObj:any):Observable<any>{
    return this.http.post("http://localhost:8083/flight/updateFlight",flightObj,httpOptions).pipe(
      tap(res=>res)
    );
  }
  public addUser(UserDetails:any):Observable<any>{
    return this.http.post("http://localhost:8083/flight/addUser",UserDetails,httpOptions).pipe(
      tap(res=>res)
    );
  }
  public searchQuery(search:any):Observable<any>{
    return this.http.post("http://localhost:8081/user/searchQuery",search,httpOptions).pipe(
      tap(res=>res)
    );
  }
  
}
