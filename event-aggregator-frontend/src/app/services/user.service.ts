import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiPath = '/api';

  constructor(private http: HttpClient) {
  }

  loginGoogle() {
    return this.http.get(`${this.apiPath}/login/google`)
  }

  loginGithub() {
    return this.http.get(`${this.apiPath}/login/github`)
  }

  getAuthentication(): Observable<any> {
    return this.http.get<any>("/api/public/users")
  }

  getUser(): Observable<any> {
    return this.http.get<any>("/api/public/users/getUser")
  }

  getToken(): Observable<string> {
    return this.http.get<string>("http://localhost:8080/users/token");
  }

}
