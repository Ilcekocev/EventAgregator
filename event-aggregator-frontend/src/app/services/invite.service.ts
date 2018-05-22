import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Rx";
import {InviteDTO} from "../model/InvitationDTO";

@Injectable({
  providedIn: 'root'
})
export class InviteService {

  constructor(private http: HttpClient) { }

  public sendInvitation(invitation: InviteDTO): Observable<any> {
    return this.http.post("/api/invite", invitation);
  }

}
