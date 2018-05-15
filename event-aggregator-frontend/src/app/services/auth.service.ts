import { Injectable, OnInit } from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";
import {User} from "../model/User";


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  currentUser: User;
  isAuthenticated: boolean;

  fetchUserObject(): Observable<User> {
    return this.http.get<any>("/api/public/users");
  }

  getPrincipal(): Observable<any> {
    return this.http.get<any>("/api/public/users/map")
  }

  validateAuthentication(): boolean {
    this.getPrincipal().subscribe(response => {
      console.log('Making http request for getting the mapped principal object!');
      this.isAuthenticated = response.name !== undefined && response.id !== 'N/A';
    });
    return this.isAuthenticated !== false;
  }


}
