import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import * as moment from 'moment';
import {EventService} from "../../services/event.service";

@Component({
  selector: 'app-events-from-range',
  templateUrl: './events-from-range.component.html',
  styleUrls: ['./events-from-range.component.css']
})
export class EventsFromRangeComponent implements OnInit{

  dates: FormGroup;
  startDateString: string;
  endDateString: string;

  constructor(private fb: FormBuilder,
              private eventService: EventService) {

  }

  ngOnInit() {
    this.dates = this.fb.group({
      startDate: [Validators.required],
      endDate: [Validators.required]
    })
    console.log("Created");
  }


  onSubmit() {
    this.startDateString = this.convertToIso(this.dates.value.startDate);
    this.endDateString = this.convertToIso(this.dates.value.endDate);
    const arrayOfNewDates:  string[] = [this.startDateString, this.endDateString];
    this.eventService.onDateChange.emit(arrayOfNewDates);
    console.log(this.startDateString);
    console.log(this.endDateString);
  }

  convertToIso(input: string): string {
    return moment(input).toISOString();
  }


}
