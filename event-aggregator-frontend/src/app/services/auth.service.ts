import { Injectable, OnInit } from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";
import {User} from "../model/User";


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  authUser: boolean;
  user: User;

  getAuthenticationObject() : Observable<any>{
    return this.http.get<any>("/api/public/users")
  }

  getUser(): Observable<any> {
    return this.http.get<any>("/api/public/users/getUser")
  }

  getPrincipal(): Observable<any> {
    return this.http.get<any>("/api/public/users/principal")
  }

  isUserAuth(): boolean {
    this.getPrincipal().subscribe(response => {
      console.log('Making http request !');
      if (response.name !== undefined && response.name !== 'N/A') {
        this.authUser = true;
      } else {
        this.authUser = false;
      }
    });
    if (this.authUser !== false && this.authUser !== undefined)
      return true;
    else {
      return false;
    }
  }

  sendUser(user: User): Observable<User> {
    return this.http.post<User>('/api/public/users/send', user);
  }

}
