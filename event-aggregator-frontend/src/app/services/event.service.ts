import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient, HttpHandler, HttpParams} from "@angular/common/http";
import {Event} from "../model/Event";
import {Observable} from "rxjs/Rx";
import {EventDTO} from "../model/EventDTO";

@Injectable({
  providedIn: 'root'
})
export class EventService {

  onDeleted = new EventEmitter<boolean>();
  onDateChange = new EventEmitter<string[]>();

  constructor(private http: HttpClient) {
  }

  createEvent(event: Event): Observable<Event> {
    return this.http.post<Event>("/api/events", event);
  }

  deleteEvent(id: number): Observable<any> {
    return this.http.delete("/api/events/" + id);
  }

  updateEvent(event: Event): Observable<Event> {
    return this.http.patch<Event>("/api/events", event);
  }

  thisWeekEvents(email: string): Observable<Event[]> {
    return this.http.get<Event[]>(`/api/events/weekly/${email}`);
  }

  fetchAllBetweenDates(start: string, end: string, userId: string): Observable<Event[]> {
    let params = {
      startTime: start,
      endTime: end,
      userId: userId
    };
    console.log(params);
    return this.http.get<Event[]>('/api/events/between', {params: params});
  }

  fetchAllPublicAfterNow(email: string): Observable<EventDTO[]> {
    return this.http.get<EventDTO[]>(`/api/events/public/${email}`);
  }



}
