import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient, HttpHandler, HttpParams} from "@angular/common/http";
import {Event} from "../model/Event";
import {Observable} from "rxjs/Rx";

@Injectable({
  providedIn: 'root'
})
export class EventService {

  onDeleted = new EventEmitter<boolean>();
  onDateChange = new EventEmitter<string[]>();

  constructor(private http: HttpClient) {
  }

  createEvent(event: Event): Observable<Event> {
    return this.http.post<Event>("/api/createdEvents", event);
  }

  deleteEvent(id: number): Observable<any> {
    return this.http.delete("/api/createdEvents/" + id);
  }

  updateEvent(event: Event): Observable<Event> {
    return this.http.patch<Event>("/api/createdEvents", event);
  }

  thisWeekEvents(email: string): Observable<Event[]> {
    return this.http.get<Event[]>(`/api/events/weekly/${email}`);
  }

  fetchAllBetweenDates(start: string, end: string, id: string): Observable<Event[]> {
    let params = {
      startTime: start,
      endTime: end,
      id: id
    };
    console.log(params);
    return this.http.get<Event[]>('/api/createdEvents/between', {params: params});
  }


}
