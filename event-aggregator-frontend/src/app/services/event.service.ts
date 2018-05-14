import { Injectable } from '@angular/core';
import {HttpClient, HttpHandler} from "@angular/common/http";
import {Event} from "../model/Event";
import {User} from "../model/User";
import {Observable} from "rxjs/Rx";

@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor(private http: HttpClient) { }

  createEvent(event: Event): Observable<Event> {
    return this.http.post<Event>("/api/events", event);
  }

  getAllPrivateEvents(): Observable<Event[]> {
   return this.http.get<Event[]>(`/api/events/private/${localStorage.getItem('id')}`);
  }

  getMockEvent(): Observable<Event> {
    return this.http.get<Event>('/api/events/mock');
  }

  getEvent(id: string) {
    this.http.get<Event>("/events/"+id);
  }

  deleteEvent(id: string) {
    this.http.delete("/api/events/"+id);
  }

  updateEvent(event: Event): Observable<Event> {
    return this.http.patch<Event>("/api/events",event);
  }
}
