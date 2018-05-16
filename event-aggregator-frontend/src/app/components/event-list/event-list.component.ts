import {Component, Input, OnInit} from '@angular/core';
import {Event} from "../../model/Event";
import {EventService} from "../../services/event.service";
import {AuthService} from "../../services/auth.service";
import {Subscription} from "rxjs/Subscription";
import 'rxjs/add/operator/debounceTime';
import {Observable} from "rxjs/Observable";

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.css']
})
export class EventListComponent implements OnInit {

  events: Event[] = undefined;
  @Input()
  startDate: string;
  @Input()
  endDate: string;
  @Input()
  thisWeek: boolean;
  numberOfColumns: number;
  userId: string;

  private deleteSubscription: Subscription;
  private datesChangeSubscription: Subscription;
  private observableFromService: Observable<Event[]>;


  constructor(private eventService: EventService,
              private authService: AuthService) { }

  ngOnInit() {
    console.log('created');
    this.userId = this.authService.currentUser.id;
    if(this.thisWeek) {
      this.observableFromService = this.eventService.fetchAllPrivateEvents(this.userId);
    }
    else if (this.startDate != undefined && this.endDate != undefined) {
      this.observableFromService = this.createObservableBetweenDates();
    }
    this.fetchData();
    this.deletingSubscription();
    this.onDatesChangesSubscription();
  }

  createObservableBetweenDates(): Observable<Event[]> {
    return this.eventService.fetchAllBetweenDates(this.startDate, this.endDate, this.userId);
  }

  fetchData() {
    this.observableFromService.subscribe(data => {
      this.events = data;
      console.log("data: {}", this.events);
      this.numberOfColumns = this.calculateColumns();
      console.log("Number of columns", this.numberOfColumns);
    });
  }

  deletingSubscription() {
    this.deleteSubscription = this.eventService.onDeleted
      .debounceTime(200)
      .subscribe(() => this.fetchData());
  }

  onDatesChangesSubscription() {
    this.datesChangeSubscription = this.eventService.onDateChange
      .subscribe((array) => {
        this.startDate = array[0];
        this.endDate = array[1];
        console.log("received data; changed dates = {}", array);
        this.observableFromService = this.createObservableBetweenDates();
        this.fetchData();
      })
  }

  calculateColumns(): number {
    if(this.events.length >= 6)
      return 6;
    return this.events.length;
  }

}
