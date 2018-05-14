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

  getAuthentication(): Observable<any> {
    return this.http.get<any>("/api/public/users")
  }

  getUser(): Observable<any> {
    return this.http.get<any>("/api/public/users/getUser")
  }


}
