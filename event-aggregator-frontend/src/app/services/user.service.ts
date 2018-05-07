import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiPath = 'http://localhost:4200/api';

  constructor(private http: HttpClient) {}

  loginGoogle() {
    return this.http.get(`${this.apiPath}/login/google`)
  }

  loginGithub() {
    return this.http.get(`${this.apiPath}/login/github`)
  }

  getUser() {
    return this.http.get(`${this.apiPath}/login`)
  }

}
