import {Component, Input, OnInit} from '@angular/core';
import {Event} from "../../model/Event";
import * as moment from 'moment';

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.css']
})
export class EventListComponent implements OnInit {

  events: Event[];
  @Input()
  startDate: string;
  @Input()
  endDate: string;
  @Input()
  numberOfDays: number;
  @Input()
  thisWeek: boolean;

  constructor() { }

  ngOnInit() {
    //sega za sega e mock
    this.createEvents();
    if(this.thisWeek) {
      //fetch eventService.getEventsForThisWeek()
    }
    else if (this.startDate != undefined && this.endDate != undefined) {
      //fetch eventService.getEventsBetweenDates(startDate, endDate);
    }
    else if (this.numberOfDays != undefined) {
      //fetch eventsService.getEventsForNextDays(numberOfDays) -- vrakja od denes do naredni denovi
    }
  }

  calculateColumns(): number {
    if(this.events.length >= 7)
      return 7;
    return this.events.length;
  }

  createEvents() {
    let event: Event = {
      description: 'Mock description',
      endTime: this.convertFromIso(),
      startTime: this.convertFromIso(),
      title: 'Mock title',
      externalLink: 'Mock link',
      id: 1,
      type: 'PRIVATE',
      emailNotification: true,
      user: 'Damjan Trickovik'
    };
    this.events = [
      event,
      event,
      event,
      event,
      event,
      event,
      event
    ]
    //replace with
  }

    convertFromIso(): string {
      return moment('05/01/2018 11:50 PM').toISOString();
    }

}
