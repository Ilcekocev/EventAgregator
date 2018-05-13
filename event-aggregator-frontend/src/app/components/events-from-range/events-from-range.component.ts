import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import * as moment from 'moment';

@Component({
  selector: 'app-events-from-range',
  templateUrl: './events-from-range.component.html',
  styleUrls: ['./events-from-range.component.css']
})
export class EventsFromRangeComponent implements OnInit {

  dates: FormGroup;
  startDateString: string;
  endDateString: string;

  constructor(private fb: FormBuilder) {

  }

  ngOnInit() {
    this.dates = this.fb.group({
      startDate: [Validators.required],
      endDate: [Validators.required]
    })
  }

  onSubmit() {
    this.startDateString = this.convertToIso(this.dates.value.startDate);
    this.endDateString = this.convertToIso(this.dates.value.endDate);
  }

  convertToIso(input: string): string {
    return moment(input).toISOString();
  }


}
