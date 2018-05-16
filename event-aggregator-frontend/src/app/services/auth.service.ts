import {Injectable, OnInit} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";
import {User} from "../model/User";


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {
  }

  currentUser: User;
  isAuthenticated: boolean = false;

  fetchUserObject(): Observable<any> {
    return this.http.get<User>("/api/public/users");
  }

  getPrincipal(): Observable<any> {
    return this.http.get<any>("/api/public/users/map")
  }

  validateAuthentication(): boolean {
    this.getPrincipal().subscribe(response => {
      console.log('Making http request for getting the mapped principal object!');
      this.isAuthenticated = response.name !== undefined && response.id !== 'N/A';
    });
    if(this.isAuthenticated != false) {
      this.fetchUserObject()
        .subscribe(data => this.currentUser = data);
      return true;
    }
    return false;
  }

}
