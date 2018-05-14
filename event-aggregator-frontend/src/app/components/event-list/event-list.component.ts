import {Component, Input, OnInit} from '@angular/core';
import {Event} from "../../model/Event";
import * as moment from 'moment';
import {EventService} from "../../services/event.service";

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

  constructor(private eventService: EventService) { }

  ngOnInit() {
    if(this.thisWeek) {
      this.eventService.getAllPrivateEvents()
        .subscribe((data: Event[]) => {
          console.log(data);
          this.events = data;
        });
    }
    else if (this.startDate != undefined && this.endDate != undefined) {
      //fetch eventService.getEventsBetweenDates(startDate, endDate);
    }
    else if (this.numberOfDays != undefined) {
      //fetch eventsService.getEventsForNextDays(numberOfDays) -- vrakja od denes do naredni denovi
    }

  }

  calculateColumns(): number {
    if(this.events.length >= 6)
      return 6;
    return this.events.length;
  }

}
